package com.sgj.ayibang.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sgj.ayibang.url.Urls;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 2016/4/19.
 * 异常处理
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    private Context mContext;
    private FinalHttp finalHttp;

    private static CrashHandler INSTANCE = new CrashHandler();
    Thread.UncaughtExceptionHandler mDefaultHandler;

    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler() {
        finalHttp = new FinalHttp();
        finalHttp.configTimeout(10 * 1000);
    }
    public static CrashHandler getInstance(){
        return INSTANCE;
    }
    public void init(Context context){
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!(handleException(ex)) && mDefaultHandler != null){
            // 系统默认异常处理器处理
            mDefaultHandler.uncaughtException(thread, ex);
        }else {
            // 退出
        }
    }

    private boolean handleException(Throwable ex){
        if(ex == null){
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        // 获取设备参数信息
        collectDeviceInfo(mContext);

        // 保存日志
        final String fileName = saveCrashInfoFile(ex);

        // 上传日志
        if(fileName!=null){

            new Thread(){
                @Override
                public void run() {
                    Looper.prepare();
                    upload(fileName);
                    Looper.loop();
                }
            }.start();
        }


        return true;
    }

    private void upload(String fileName) {
        try {
            final File file = new File(fileName);
            AjaxParams ajaxParams = new AjaxParams();
            ajaxParams.put("file", file);
            finalHttp.post(Urls.EXCEPTION_URL, ajaxParams, new AjaxCallBack<String>() {
                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onSuccess(String str) {
                    super.onSuccess(str);
                    if(!TextUtils.isEmpty(str)){
                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            if(jsonObject.optInt("result") == 1){
                                file.delete();
                                Log.d(TAG, "上传成功");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "上传失败");
                        }

                    }
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String saveCrashInfoFile(Throwable ex) {
        StringBuffer stringBuffer = new StringBuffer();

        for(Map.Entry<String, String> entry : infos.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key + " = " + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        stringBuffer.append(result);

        long timestamp = System.currentTimeMillis();

        String time = formatter.format(new Date());
        String fileName = "crash-"+ time + "-" + timestamp + ".log";


        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream outputStream = new FileOutputStream(path + fileName);
                outputStream.write(stringBuffer.toString().getBytes());

                outputStream.close();
            }
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取设备参数信息
     * @param context
     */
    private void collectDeviceInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if(info != null){
                String versionName = info.versionName == null ? "null" : info.versionName;
                String versionCode = info.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = Build.class.getDeclaredFields();

        try {
            for(Field field : fields) {
                field.setAccessible(true);
                // 如果字段是静态字段的话,传入任何对象都是可以的,包括null
                infos.put(field.getName(), field.get(null).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
