package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v3.SnifferSmartLinker;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */

public class ConnectioningWifiActivity extends BaseActivity  implements OnSmartLinkListener {

    //保存设备mac
    private String mDeviceMac;
    //设备名
    private String mDeviceName;
    //用于进度
    private int sumProgress;
    //进度定时器
    private Timer mProgressTimer;
    //wifi名
    private String wifiName;
    //wifi密码
    private String wifiPass;
    //取消按钮
    private Button mCancelBtn;
    //用于更新界面
    private Handler mViewHandler = new Handler();
    //wifi配置
    protected ISmartLinker mSnifferSmartLinker;

    public static final String EXTRA_SMARTLINK_VERSION = "EXTRA_SMARTLINK_VERSION";

    //进度按钮
    private TextView mProgressCp;
    //进度任务
    private TimerTask mProgressTask = new TimerTask() {

        public void run() {
            if (sumProgress < 40) {
                sumProgress += new Random().nextInt(5);
            } else if (sumProgress >= 40 && sumProgress < 80) {
                sumProgress += new Random().nextInt(5);
            } else if (sumProgress >= 80 && sumProgress < 99) {
                sumProgress += new Random().nextInt(2);
            } else {
            }
            mViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressCp.setText("" + sumProgress);
                }
            });
        }
    };



    @Override
    protected void initView() {
        setContentView(R.layout.activity_conntioning_wifi);
    }

    @Override
    protected void initData() {
        mCancelBtn = (Button) findViewById(R.id.link_btn);
        mProgressCp = (TextView) findViewById(R.id.link_devicing_text);
        wifiName = getIntent().getStringExtra("WifiName");
        Log.i("---->wifiName", wifiName);
        wifiPass = getIntent().getStringExtra("WifiPass");
        Log.i("---->WifiPass", wifiPass);

        /**
         * 初始化 用于配置wifi
         * */
        int smartLinkVersion = getIntent().getIntExtra(EXTRA_SMARTLINK_VERSION, 3);
        Log.i("--->smartLinkVersion", "" + smartLinkVersion);
//        mSnifferSmartLinker = MulticastSmartLinker.getInstance();


        if (smartLinkVersion == 7) {
            mSnifferSmartLinker = MulticastSmartLinker.getInstance();
        } else {
            mSnifferSmartLinker = SnifferSmartLinker.getInstance();
        }
        mProgressTimer = new Timer("ProgressTimer");
        mSnifferSmartLinker.setTimeoutPeriod(30 * 1000);
        mSnifferSmartLinker.setOnSmartLinkListener(this);

        mViewHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mSnifferSmartLinker.start(ConnectioningWifiActivity.this, wifiPass, wifiName);
                    mProgressTimer.schedule(mProgressTask, 0, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },2000);



        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelConfigHandle();
//                startActivity(new Intent(ConnectioningWifiActivity.this, ReConntionActivity.class));
                finish();
            }
        });

    }


    private void initDate(){

    }

    //停值
    private void stopTimer() {
        if (mProgressTimer != null) {
            mProgressTask.cancel();
            mProgressTimer.cancel();
            mProgressTimer = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cancelConfigHandle();
        }
        return true;
    }

    /**
     * 取消配网处理方法
     */
    private void cancelConfigHandle() {
        stopTimer();
        mSnifferSmartLinker.setOnSmartLinkListener(null);
        mSnifferSmartLinker.stop();
        finish();
    }

    @Override
    public void onLinked(final SmartLinkedModule smartLinkedModule) {
        mViewHandler.post(new Runnable() {
            @Override
            public void run() {
                mDeviceMac = smartLinkedModule.getMac();
                Log.e("---->mDeviceMac",mDeviceMac);
                Toast.makeText(ConnectioningWifiActivity.this, getResources().getString(R.string.lock_dervice), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCompleted() {
        mViewHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ConnectioningWifiActivity.this,getResources().getString(R.string.connect_true), Toast.LENGTH_SHORT).show();
                cancelConfigHandle();
                change(DerviceItemActivity.class,ConnectioningWifiActivity.this,new Intent(),false);

            }
        });
    }


    @Override
    public void onTimeOut() {
        mViewHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelConfigHandle();
                Toast.makeText(ConnectioningWifiActivity.this, getResources().getString(R.string.time_out), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
