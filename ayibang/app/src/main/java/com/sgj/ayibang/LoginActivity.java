package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sgj.ayibang.model.Contact;
import com.sgj.ayibang.receiver.SMSBroadcastReceiver;
import com.sgj.ayibang.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.bean.BmobSmsState;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.QuerySMSStateListener;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by John on 2016/4/11.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static Context mContext;
    private static final String TAG = "LoginActivity";

    private SMSBroadcastReceiver mSMSBroadcastReceiver;
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private static final int RETRY_INTERVAL = 10; //设置一个倒计时时间
    private int time = RETRY_INTERVAL;

    @Bind(R.id.btn_authcode)
    Button btnAuthCode;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.et_phone_number)
    EditText etPhoneNumber;
    @Bind(R.id.et_pass_word)
    EditText etPassWord;

    String mPhoneNumber;
    String mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //生成广播处理
        mSMSBroadcastReceiver = new SMSBroadcastReceiver();

        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        //注册广播
        this.registerReceiver(mSMSBroadcastReceiver, intentFilter);

        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
            @Override
            public void onReceived(String message) {

                etPassWord.setText(message);

            }
        });
    }

    // 按钮是否可点击 true ：可点击，
    private boolean btnAuthCodeState = false;
    private boolean btnLoginState = false;

    private void init() {
        btnAuthCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnAuthCode.setClickable(false);
        btnLogin.setClickable(false);

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                toast("CharSequence s "+ s +", int start "+ start +", int before "+ before +", int count " + count);
                if(s.length() > 10){
                    btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
                    btnAuthCode.setClickable(true);
                    btnAuthCodeState = true;
                }else {
                    btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
                    btnAuthCode.setClickable(false);
                    btnAuthCodeState = false;
                }
                btnState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                toast("CharSequence s "+ s +", int start "+ start +", int before "+ before +", int count " + count);
                if(s.length() > 3){
                    btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
                    btnLogin.setClickable(true);
                    btnLoginState = true;
                }else {
                    btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
                    btnLogin.setClickable(false);
                    btnLoginState = false;
                }
                btnState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void btnState(){
        if(btnAuthCodeState && btnLoginState){
            btnAuthCode.setClickable(true);
            btnLogin.setClickable(true);
            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
        }
        if(btnAuthCodeState && !btnLoginState){
            btnAuthCode.setClickable(true);
            btnLogin.setClickable(false);
            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
        }
        if(!btnAuthCodeState && !btnLoginState){
            btnAuthCode.setClickable(false);
            btnLogin.setClickable(false);
            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
        }
        if(!btnAuthCodeState && btnLoginState){
            btnAuthCode.setClickable(false);
            btnLogin.setClickable(false);
            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        this.unregisterReceiver(mSMSBroadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_authcode:
                mPhoneNumber = etPhoneNumber.getText().toString();
                toast(" 获取短信验证码 ");

                String smsId = sendSMS(mContext, mPhoneNumber);
                countDown();

//                if(smsId != null){
//                    Map<String, String> map = querySMS(mContext, Integer.parseInt(smsId));
//                    String SmsState = map.get("SmsState");
//                    switch (SmsState){
//                        case "SUCCESS":
//                            countDown();
//                            break;
//                        case "FAIL":
//                            break;
//                        case "SENDING":
//                            break;
//                        default:
//                            break;
//                    }
//                }
                break;
            case R.id.btn_login:
                mPassWord = etPassWord.getText().toString();
                toast(" 登陆 ");
                Map<String, String> map = verifySMS(mContext, mPhoneNumber, mPassWord);
                if(map.get("code") == null){ // 验证成功

                    LoginActivity.this.finish();
                }

                break;
            default:
                break;
        }
    }

    // 发送验证码
    public static final String sendSMS(Context context, String phone){

        final String[] mSMSId = new String[1];

        BmobSMS.requestSMSCode(context, phone, Constant.MSM_NAME, new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) { // 发送成功
                    Log.i(TAG, " 短信验证码 ID ：" + smsId); // 用于查询本次短信发送详情
                    mSMSId[0] = smsId + "";
                }
            }
        });
        return mSMSId[0];
    }

    // 验证验证码
    public static final Map<String, String> verifySMS(Context context, String phone, String sms){
        final Map<String, String> map = new HashMap<>();
        BmobSMS.verifySmsCode(context, phone, sms, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) { // 验证成功

                } else {
                    Log.i("bmob", "验证失败：code =" + e.getErrorCode() + ",msg = " + e.getLocalizedMessage());
                    map.put("code", e.getErrorCode() + "");
                    map.put("msg", e.getLocalizedMessage());
                }
            }
        });
        return map;
    }

    // 短息发送状态
    public static final Map<String, String> querySMS(Context context, int smsId){
        final Map<String, String> map = new HashMap<>();
        BmobSMS.querySmsState(mContext, smsId, new QuerySMSStateListener() {
            @Override
            public void done(BmobSmsState bmobSmsState, BmobException e) {
                // smsState（短信状态） :SUCCESS（发送成功）、FAIL（发送失败）、SENDING(发送中)
                // verifyState（验证状态）:true(已验证)、false(未验证)
                if (e == null) {
                    Log.i("bmob", "短信状态：" + bmobSmsState.getSmsState() + ",验证状态：" + bmobSmsState.getVerifyState());
                    map.put("SmsState", bmobSmsState.getSmsState());
                    map.put("VerifyState", bmobSmsState.getVerifyState());
                }
            }
        });
        return map;
    }

    //倒计时方法
    private void countDown(){
        btnAuthCode.setClickable(false);
        btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
        new Thread(new  Runnable() {
            public void run() {
                while(time-- > 0){
                    final String unReceive = LoginActivity.this.getResources().getString(R.string.smssdk_receive_msg, time);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            btnAuthCode.setText(Html.fromHtml(unReceive));
//                            btnAuthCode.setEnabled(false);
                            btnAuthCode.setClickable(false);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        btnAuthCode.setText("获取验证码");
                        btnAuthCode.setClickable(true);
                        btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
                    }
                });

                time = RETRY_INTERVAL;
            }
        }).start();
    }

    private void toast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
