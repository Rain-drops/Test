package com.sgj.ayibang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.sgj.ayibang.fragment.SlidingUpPanelContentFragment;
import com.sgj.ayibang.fragment.SlidingUpPanelMenuFragment;

/**
 * Created by John on 2016/5/16.
 */
public class SlidingUpPanelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_up_panel);
        init();
    }

    private void init() {
        SlidingUpPanelContentFragment fragment = SlidingUpPanelContentFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();

        new initQuickControls().execute("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化快速控制
     */
    public class initQuickControls extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            SlidingUpPanelMenuFragment fragment = SlidingUpPanelMenuFragment.getInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.quickcontrols_container, fragment).commitAllowingStateLoss();
            return "Executed";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
//            SlidingUpPanelMenuFragment.topContainer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, " QuickControlsFragment.topContainer.setOnClickListener ");
//                }
//            });
        }
    }
}
