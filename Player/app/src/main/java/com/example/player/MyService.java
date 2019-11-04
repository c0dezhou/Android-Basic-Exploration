package com.example.player;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;


import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyService extends Service {

    Context context;
    @Override
    public void  onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = getApplicationContext();
    }
    //创建通知
    public void  CreateInform() {
        //定义一个PendingIntent，当用户点击通知时，跳转到某个Activity(也可以发送广播等)

        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        //创建一个通知
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//新建一个Notification管理器;
//API level 11
        Notification.Builder builder = new Notification.Builder(this);//新建Notification.Builder对象
        PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, VideoListActivity.class), 0);
//PendingIntent点击通知后所跳转的页面
        builder.setContentTitle("播放情况");
        builder.setContentText("视频正在播放");
        builder.setSmallIcon(R.drawable.info);
        builder.setContentIntent(intent);//执行intent
        Notification notification = builder.getNotification();//将builder对象转换为普通的notification
        notification.flags = Notification.FLAG_AUTO_CANCEL;//点击通知后通知消失
        manager.notify(1,notification);//运行notification



        //用NotificationManager的notify方法通知用户生成标题栏消息通知

        //如果拥有相同id的通知已经被提交而且没有被移除，该方法会用更新的信息来替换之前的通知。
    }

    @Override
    public void  onStart(Intent intent, int startId) {
        // TODO Auto-generated method stubm
        super.onStart(intent, startId);
        CreateInform();
    }

    @Override
    public void  onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return new MyBinder();
    }

    public class MyBinder extends Binder{
        public MyService getMyService(){
            return MyService.this;
        }
    }


}

