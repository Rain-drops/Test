package com.sgj.ayibang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class AdvertiseActivity extends Activity implements View.OnClickListener{

    @Bind(R.id.iv_home_pop_close)
    ImageView popClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);
        ButterKnife.bind(this);
        popClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_pop_close:
                this.finish();
                break;
        }
    }
}
