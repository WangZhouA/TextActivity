package myapplication66.yanglh6.example.com.textactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by 陈姣姣 on 2018/7/28.
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--->被启动了","service");

        IntentFilter intentFilter =new IntentFilter("JG");
        intentFilter.addAction("clear");
        registerReceiver(broadcastReceiver,intentFilter);

    }


    int count=0;
    Intent intent1;
    private BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();
            if (action.contains("JG")){
                count= count+1;
//                SPUtils.putString(MyService.this,"CONUNT",count+"");

                intent1 = new Intent("con");
                intent1.putExtra("count",count);
                Log.e("--->",count+"");
                MyService.this.sendBroadcast(intent1);
            }else if (action.contains("clear")){
                count=0;
                intent1 = new Intent("CLEAR");
                MyService.this.sendBroadcast(intent1);
            }

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
