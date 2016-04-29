package com.sgj.ayibang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sgj.ayibang.db.PersonDB;
import com.sgj.ayibang.model.Person;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by John on 2016/4/8.
 */
public class ImageViewActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "ImageViewActivity";

    @Bind(R.id.fab_sub)
    FloatingActionButton mFabSub;
    private Object data;
    Context mContext;
    @Bind(R.id.sdv_fresco)
    SimpleDraweeView sdv_fresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageview);
        mContext = this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mFabSub.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_sub:
                new savePerson().execute();
//                getData();
                break;
            default:
                break;
        }
    }

    public void getData() {

        String mPhoneNumber = "15011386125";

        final Person person = new Person(mPhoneNumber, mPhoneNumber, 0);

        SharedPreferences preferences = mContext.getSharedPreferences(LoginActivity.USER, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        String phone = preferences.getString("phone", "");
        if(TextUtils.isEmpty(phone)){

            editor.putString("name", mPhoneNumber);
            editor.putString("phone", mPhoneNumber);
            editor.commit();


            new savePerson().execute(person);

            person.save(mContext, new SaveListener() {
                @Override
                public void onSuccess() {
                    Log.e(TAG, "添加数据成功，返回objectId为：" + person.getObjectId() + ",数据在服务端的创建时间为：" + person.getCreatedAt());
                }

                @Override
                public void onFailure(int code, String msg) {
                    // code：401, msg : unique index cannot has duplicate value: 15011386125
                    Log.e(TAG, "添加数据失败，code：" + code + ", msg : " + msg);
                }
            });
        }
        if(phone.equals(mPhoneNumber)){

        }else {

        }
    }

    private class savePerson extends AsyncTask<Person, Void, Void> {


        @Override
        protected Void doInBackground(Person... params) {
            PersonDB personDB = PersonDB.getInstance(mContext);
//            personDB.savePerson(params[0]);
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(new Person("15011386126", "15011386126", 0));
            persons.add(new Person("15011386127", "15011386127", 0));
            personDB.savePerson(persons);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
