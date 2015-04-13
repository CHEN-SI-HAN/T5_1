package idv.leo.t5_1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 *
 */
public class Service extends android.app.Service{
    private final IBinder binder = new ServiceBinder();

    public class ServiceBinder extends Binder {

        Service getService() {
            return Service.this;
        }
    }



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override

    public IBinder onBind(Intent intent) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setTicker("Service Message")
                .setSmallIcon(R.drawable.ic_secret_notification)
                .setContentTitle("開啟完成，產生Notification！")
                .setContentText("This is content text")
                .setAutoCancel(true)
                .build();
        notificationManager.notify(10, notification);
        return binder;
    }

    @Override
    // 沒有任何client連結Service時會呼叫此方法
    public boolean onUnbind(Intent intent) {

        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

