package com.sgj.ayibang.utils;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobSmsState;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QuerySMSStateListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;


/**
 * Created by John on 2016/4/12.
 */
public class SendSMSUtils {

    // 发送验证码
    public static final int sendSMS(Context context, String phone){

        final int[] mSmsId = new int[1];

        BmobSMS.requestSMSCode(context, phone, Constant.MSM_NAME, new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) { // 发送成功
                    Log.i("bmob", " 短信验证码 ID ：" + smsId); // 用于查询本次短信发送详情
                    mSmsId[0] = Integer.parseInt(smsId+"");

                }
            }
        });

        return mSmsId[0];
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
        BmobSMS.querySmsState(context, smsId, new QuerySMSStateListener() {
            @Override
            public void done(BmobSmsState bmobSmsState, BmobException e) {
                // smsState（短信状态） :SUCCESS（发送成功）、FAIL（发送失败）、SENDING(发送中)
                // verifyState（验证状态）:true(已验证)、false(未验证)
                if(e == null){
                    Log.i("bmob","短信状态："+bmobSmsState.getSmsState()+",验证状态："+bmobSmsState.getVerifyState());
                    map.put("SmsState", bmobSmsState.getSmsState());
                    map.put("VerifyState", bmobSmsState.getVerifyState());

                }
            }
        });
        return map;
    }
}
