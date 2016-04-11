package com.sgj.ayibang;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/11.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Context mContext;

    @Bind(R.id.btn_authcode)
    Button btnAuthCode;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.et_phone_number)
    EditText etPhoneNumber;
    @Bind(R.id.et_pass_word)
    EditText etPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        init();
    }

    private void init() {
        btnAuthCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        etPhoneNumber.addTextChangedListener(mTextWatcher);
//        if(s.length() > 10){
//            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
//        }else {
//            btnAuthCode.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
//        }

        etPassWord.addTextChangedListener(mTextWatcher);
//        if(s.length() > 3 && (etPhoneNumber.getText().length() == 11) ){
//            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.click_rounded_corners_button));
//        }else {
//            btnLogin.setBackground(ContextCompat.getDrawable(mContext, R.drawable.unclick_rounded_corners_button));
//        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            toast("CharSequence s "+ s +", int start "+ start +", int before "+ before +", int count " + count);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {

    }

    private void toast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
