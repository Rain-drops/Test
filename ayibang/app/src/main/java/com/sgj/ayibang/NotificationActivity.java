package com.sgj.ayibang;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/1.
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "NotificationActivity";
    private static final int NOTIFICATION_FLAG = 1;

    @Bind(R.id.btn_button1)
    Button button1;

    @Bind(R.id.btn_button2)
    Button button2;

    static final int NOTIFICATION_ID = 0x1123 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_button1:
                notification();
                break;
            case R.id.btn_button2:
                cancelNotification();
                break;
        }
        notification();
    }

    private void notification() {

        //获取系统的NotificationManager服务
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //创建一个启动其他Activity的Intent
        Intent intent = new Intent(NotificationActivity.this
                , NotificationActivity.class);
        PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this
                , 0, intent , 0);

        /*
        Notification notify3 = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_close)
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                .setContentTitle("Notification Title")
                .setContentText("This is the notification message")
                .setContentIntent(pi).setNumber(1).build(); // 需要注意build()是在API
        // level16及之后增加的，API11可以使用getNotificatin()来替代
        notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        notificationManager.notify(NOTIFICATION_FLAG, notify3);
        */

        Notification myNotify = new Notification();
        myNotify.icon = R.drawable.ic_close;
        myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
        myNotify.when = System.currentTimeMillis();
//        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
        RemoteViews rv = new RemoteViews(getPackageName(),
                R.layout.my_notification);
        rv.setTextViewText(R.id.text_content, "hello wrold!");
        myNotify.contentView = rv;
        myNotify.contentIntent = pi;
        notificationManager.notify(NOTIFICATION_FLAG, myNotify);

    }

    private void cancelNotification(){
        /*
        //获取系统的NotificationManager服务
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        //取消通知
        notificationManager.cancel(NOTIFICATION_FLAG);
        */
    }
}
