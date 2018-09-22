package myapplication66.yanglh6.example.com.textactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.activity.DebitNoteActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.DerviceItemActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.StartConntionWifiActivity;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.DayDateBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.UserBean;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NotifiyCallBack;
import myapplication66.yanglh6.example.com.textactivity.photo.PhotoClipActivity;
import myapplication66.yanglh6.example.com.textactivity.photo.PhotoUtil;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.LogUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.Util;
import myapplication66.yanglh6.example.com.textactivity.utils.mpandroidchart.WZ_lineChart;
import myapplication66.yanglh6.example.com.textactivity.utils.mpandroidchart.WZ_lineChartCopy;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class MainActivity extends BaseActivity implements  DrawerLayout.DrawerListener,HttpRequestCallback ,NotifiyCallBack{


    @BindView(R.id.header_left)
    ImageButton headerRight;

    @BindView(R.id.header_right_msg)
    TextView headerRightMsg;



    DrawerLayout mDrawerLayout;
    //左边布局
    View mLeftMenuFragment;
    @BindView(R.id.rl_noNetWork)
    RelativeLayout rlNoNetWork;
    @BindView(R.id.rl_noTaoCan)
    RelativeLayout rlNoTaoCan;
    @BindView(R.id.history_day)
    TextView historyDay;
    @BindView(R.id.history_week)
    TextView historyWeek;
    @BindView(R.id.history_mouth)
    TextView historyMouth;
    @BindView(R.id.history_ji)
    TextView historyJi;

    TextView [] textViews;
    @BindView(R.id.LineChartView)
    LineChart lineChartView;
    @BindView(R.id.close_im_monery)
    ImageView closeMonery;

    @BindView(R.id.LineChartTwo)
    LineChart LineChartTwo;

    @BindView(R.id.LineChartThree)
    LineChart LineChartThree;
    @BindView(R.id.line_chart_six)
    LineChart LineChartSix;

    // 头像处理
    PhotoUtil photoUtil;

    List<Integer>listIntOne= new ArrayList<>();
    List<String>listIntOneXstr= new ArrayList<>();
    List<String>listIntTwoXstr= new ArrayList<>();
    List<Integer>listIntTwo= new ArrayList<>();
    List<String>listIntThreeXstr= new ArrayList<>();
    List<Integer>listIntThree = new ArrayList<>();
    List<Integer>listIntFour = new ArrayList<>();
    List<Integer>listIntFive = new ArrayList<>();
    List<Integer>listIntSix = new ArrayList<>();
    List<String>listIntFourXstr= new ArrayList<>();

    /**
     * 在详情界面会用到的，这里要完整的数据，先存起来吧
     * **/
    List<String>listIntOneXstrCopy= new ArrayList<>();







    private String days="";
    private int  DayFlag_SET_ZOOM =0;


    @BindView(R.id.tv_mean_ultrafiltration)
    TextView FuTouUltrafiltration;

    @BindView(R.id.tv_Ultrafiltration_difference)
    TextView FuTouDifference;

    @BindView(R.id.tv_futou_yanse)
    TextView FuTouColoer;

    @BindView(R.id. tv_rising)
    TextView FuTouUp_Down;


    @BindView(R.id.tv_mean_ultrafiltration_Urine_output)
    TextView  LiaoLiangUltrafiltration;


    @BindView(R.id.tv_Difference_of_urine)
    TextView  LiaoLiangDifference;


    @BindView(R.id.tv_liaoliang_yanse)
    TextView LiaoLiangColoer;

    @BindView(R.id.tv_rising_urine)
    TextView LiaoLiangUp_Down;




    @BindView(R.id.tv_mean_ultrafiltration_weight)
    TextView WeightUltrafiltration;

    @BindView(R.id.tv_mean_difference_weight)
    TextView WeightDifference;

    @BindView(R.id.tv_weight_yanse)
    TextView WeightColoer;

    @BindView(R.id.tv_rising_weight)
    TextView WeightUp_Down;



    @BindView(R.id.tv_triad_High)
    TextView TriadHighUltrafiltration;
    @BindView(R.id.tv_High_pressure_difference)
    TextView TriadHighDifference;
    @BindView(R.id.tv_High_pressure_color)
    TextView HighColor;
    @BindView(R.id.tv_rising_height)
    TextView heightUp_Down;
    @BindView(R.id.tv_Low__average)
    TextView TriadLowUltrafiltration;
    @BindView(R.id.tv_Low_difference)
    TextView  TriadLowDifference;
    @BindView(R.id.tv_Low_color)
    TextView LowColor;
    @BindView(R.id.tv_rising_Low_difference)
    TextView LowUp_Down;
    @BindView(R.id.tv_The_mean_heart_rate)
    TextView heartUltrafiltration;
    @BindView(R.id.tv_The_mean_heart_difference)
    TextView heartDifference;
    @BindView(R.id.tv_The_mean_heart_color)
    TextView heartColor;
    @BindView(R.id.tv_rising_down_up)
    TextView  heartUp_Down;


    private int  dayA=1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    HttpUtils.getInstance(MainActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE + StringUtils.USER + SPUtils.getInt(MainActivity.this, MyConstaints.USER_ID, 0), new HttpRequestCallback() {
                        @Override
                        public void onResponse(String sequest, int type) {
                            Gson gson =new Gson();
                            if (type==0x01){

                                userBean = gson.fromJson(sequest,UserBean.class);

                                Intent intent =new Intent("AHeader");
                                intent.putExtra("header",userBean.getData().getHeadUrl());
                                Log.d("---->传的图片",userBean.getData().getHeadUrl());
                                sendBroadcast(intent);

                            }
                        }

                        @Override
                        public void onFailure(String exp) {

                        }
                    });

                    break;
                case  10:
                    rlNoNetWork.setVisibility(View.VISIBLE);
                    rlNoTaoCan.setVisibility(View.GONE);
                    break;
                case  1:
                    rlNoNetWork.setVisibility(View.GONE);
                    break;
                case  2:
                    rlNoNetWork.setVisibility(View.VISIBLE);
                    break;

                case  3:
                    rlNoTaoCan.setVisibility(View.GONE);
                    break;
                case  4:
                    rlNoTaoCan.setVisibility(View.VISIBLE);
                    break;
                case  5:
                    Log.e("----->6","6");
                    listIntent = listIntOneXstr;
                    listIntentCOPY = listIntOneXstrCopy;

                    /**
                     *  这里2个list的数据好像没对上，需要对比数据
                     * */


                    OneLinChart(dayA,listIntOneXstr,lineChartView,listIntOne,Color.parseColor("#20A0FF"),1,DayFlag_SET_ZOOM,0);
                    Log.e("----->7","7");
                    OneLinChart(dayA,listIntTwoXstr ,LineChartTwo,listIntTwo ,Color.parseColor("#20A0FF"),2,DayFlag_SET_ZOOM,1);
                    Log.e("----->8","8");

                    OneLinChart(dayA,listIntThreeXstr ,LineChartThree,listIntThree ,Color.parseColor("#20A0FF"),2,DayFlag_SET_ZOOM,2);
                    Log.e("----->9","9");
                    OneLinChartDouble(dayA,listIntFourXstr ,LineChartSix,listIntFour,listIntFive,listIntSix,DayFlag_SET_ZOOM,3);
                    Log.e("----->10","10");

                    // 第一张图的均值
                    FuTouUltrafiltration.setText( Util.junZhi(listIntOne)+"");
                    int cha= Util.Max(listIntOne)-Util.Min(listIntOne);
                    // 第一张图的差值
                    FuTouDifference.setText(cha+"");
                    Log.e("----->11","11");
                    // 第一张上升或者下降
                    int value =listIntOne.get(listIntOne.size()-1) -listIntOne.get(0);
                    if (value>0){
                        FuTouColoer.setTextColor(Color.parseColor("#FF0000"));
                        FuTouUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        FuTouColoer.setText(getResources().getString(R.string.Up));
                        FuTouUp_Down.setText(value+"");
                    } else {
                        FuTouColoer.setTextColor(Color.parseColor("#13CE66"));
                        FuTouColoer.setText(getResources().getString(R.string.down));
                        FuTouUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        FuTouUp_Down.setText(Math.abs(value)+"");
                    }
                    Log.e("----->12","12");


                    LiaoLiangUltrafiltration.setText(Util.junZhi(listIntTwo)+"");
                    int LiaoLiangcha= Util.Max(listIntTwo)-Util.Min(listIntTwo);
                    // 第二张图的差值
                    LiaoLiangDifference.setText(LiaoLiangcha+"");


                    // 第二张上升或者下降
                    int LiaoLiangvalue =listIntTwo.get(listIntTwo.size()-1) -listIntTwo.get(0);
                    if (LiaoLiangvalue>0){
                        LiaoLiangColoer.setTextColor(Color.parseColor("#FF0000"));
                        LiaoLiangColoer.setText(getResources().getString(R.string.Up));
                        LiaoLiangUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        LiaoLiangUp_Down.setText(LiaoLiangvalue+"");

                    } else {
                        LiaoLiangColoer.setTextColor(Color.parseColor("#13CE66"));
                        LiaoLiangColoer.setText(getResources().getString(R.string.down));
                        LiaoLiangUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        LiaoLiangUp_Down.setText(Math.abs(LiaoLiangvalue)+"");
                    }


                    Log.e("----->13","13");

                    WeightUltrafiltration.setText(Util.junZhi(listIntThree)+"");
                    int Weightcha= Util.Max(listIntThree)-Util.Min(listIntThree);
                    WeightDifference.setText(Weightcha+"");


                    // 第二张上升或者下降
                    int Weightvalue =listIntThree.get(listIntThree.size()-1) -listIntThree.get(0);
                    if (Weightvalue>0){
                        WeightColoer.setTextColor(Color.parseColor("#FF0000"));
                        WeightColoer.setText(getResources().getString(R.string.Up));
                        WeightUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        WeightUp_Down.setText(Weightvalue+"");
                    } else {
                        WeightColoer.setTextColor(Color.parseColor("#13CE66"));
                        WeightColoer.setText(getResources().getString(R.string.down));
                        WeightUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        WeightUp_Down.setText(Math.abs(Weightvalue)+"");
                    }

                    Log.e("----->14","14");


                    TriadHighUltrafiltration.setText(Util.junZhi(listIntFour)+"");
                    int Highcha= Util.Max(listIntFour)-Util.Min(listIntFour);
                    TriadHighDifference .setText(Highcha+"");

                    // 第二张上升或者下降
                    int Highvalue =listIntFour.get(listIntFour.size()-1) -listIntFour.get(0);
                    if (Highvalue>0){
                        HighColor.setTextColor(Color.parseColor("#FF0000"));
                        HighColor.setText(getResources().getString(R.string.Up));
                        heightUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        heightUp_Down.setText(Highvalue+"");
                    } else {
                        HighColor.setTextColor(Color.parseColor("#13CE66"));
                        HighColor.setText(getResources().getString(R.string.down));
                        heightUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        heightUp_Down.setText(Math.abs(Highvalue)+"");
                    }

                    Log.e("----->15","15");

                    TriadLowUltrafiltration.setText(Util.junZhi(listIntFive)+"");
                    int Lowcha= Util.Max(listIntFive)-Util.Min(listIntFive);
                    TriadLowDifference.setText(Lowcha+"");
                    // 第二张上升或者下降
                    int Lowvalue =listIntFive.get(listIntFive.size()-1) -listIntFive.get(0);
                    if (Lowvalue>0){
                        LowColor.setTextColor(Color.parseColor("#FF0000"));
                        LowColor.setText(getResources().getString(R.string.Up));
                        LowUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        LowUp_Down.setText(Lowvalue+"");
                    } else {
                        LowColor.setTextColor(Color.parseColor("#13CE66"));
                        LowColor.setText(getResources().getString(R.string.down));
                        LowUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        LowUp_Down.setText(Math.abs(Lowvalue)+"");
                    }


                    Log.e("----->16","16");

                    heartUltrafiltration.setText(Util.junZhi(listIntSix)+"");
                    int heartcha= Util.Max(listIntSix)-Util.Min(listIntSix);
                    heartDifference.setText(heartcha+"");

                    // 第二张上升或者下降
                    int heartalue =listIntSix.get(listIntSix.size()-1) -listIntSix.get(0);

                    if (heartalue>0){
                        heartColor.setTextColor(Color.parseColor("#FF0000"));
                        heartColor.setText(getResources().getString(R.string.Up));
                        heartUp_Down.setTextColor(Color.parseColor("#FF0000"));
                        heartUp_Down.setText(heartalue+"");
                    } else {
                        heartColor.setTextColor(Color.parseColor("#13CE66"));
                        heartUp_Down.setTextColor(Color.parseColor("#13CE66"));
                        heartUp_Down.setText(Math.abs(heartalue)+"");
                    }
                    Log.e("----->17","17");
                    break;
            }
        }
    };


    @Override
    protected void initView() {

        setContentView(R.layout.activity_main);

    }
    Intent intentServicer;
    @Override
    protected void initData() {


        //侧滑
        mDrawerLayout = findViewById(R.id.unbind_drawer);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        mDrawerLayout.setDrawerListener(this);
        Util.setDrawerLeftEdgeSize(this, mDrawerLayout, 0.1f);
        mLeftMenuFragment = findViewById(R.id.left_menu);


        setLinstenr();
        textViews =new TextView[]{historyDay,historyWeek,historyMouth,historyJi};
        setTile(getResources().getString(R.string.Intelligent_electronic_scale));
        headerRightMsg.setText(getResources().getString(R.string.dervice));
        headerRightMsg.setVisibility(View.VISIBLE);

        /**
         *    广播
         ***/
        intentServicer = new Intent(MainActivity.this,MyService.class);
        startService(intentServicer);


        IntentFilter intentFilter =  new IntentFilter();
        intentFilter.addAction("Header");
        registerReceiver(broadcastReceiver,intentFilter);


        days="7";
        DayFlag_SET_ZOOM =  1;
        setSelect(0);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(broadcastReceiver);
        stopService(intentServicer);

    }

    @Override
    protected void onResume() {
        super.onResume();

        HttpUtils.getInstance(MainActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.USER+ SPUtils.getInt(this, MyConstaints.USER_ID,0) ,this);
        Log.e("----days",days);
        setDayUpdate(days);

        if (checkNet() == true) {
        } else {
            rlNoNetWork.setVisibility(View.VISIBLE);
            Toast.makeText(this, getResources().getString(R.string.not_networks_conntion), Toast.LENGTH_SHORT).show();
        }
    }

    private void setLinstenr() {
        backImageView(R.mipmap.list);
    }


    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - mExitTime < 2000) {
                MyApplication.getInstance().exit();
            } else {
                mExitTime = currentTime;
                toast("在按一次退出程序");
            }
        }
        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        LogUtils.e("1");

        View mContent = mDrawerLayout.getChildAt(0);
        float scale = 1 - slideOffset;
        if (drawerView.getTag().equals("LEFT")) {

            float leftScale = 1 - 0.3f * scale;

            ViewHelper.setScaleX(drawerView, leftScale);
            ViewHelper.setScaleY(drawerView, leftScale);
            ViewHelper.setTranslationX(mContent, drawerView.getMeasuredWidth()
                    * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
        } else {
            ViewHelper.setTranslationX(mContent, -drawerView.getMeasuredWidth()
                    * slideOffset);
            ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
        }
        ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
        mContent.invalidate();


    }

    @Override
    public void onDrawerOpened(View drawerView) {
        /**
         *  跟新写在这里
         * */
        Log.e("---->用户Id",SPUtils.getInt(this, MyConstaints.USER_ID,0)+"");
        HttpUtils.getInstance(MainActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.USER+ SPUtils.getInt(this, MyConstaints.USER_ID,0) ,this);


    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        LogUtils.e("4");
    }


    @OnClick({R.id.rl_noNetWork, R.id.rl_noTaoCan,R.id.header_right_msg, R.id.history_day, R.id.history_week, R.id.history_mouth, R.id.history_ji,R.id.header_left,R.id.close_im_monery})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.header_left:
                mDrawerLayout.openDrawer(mLeftMenuFragment);
                break;
            case R.id.rl_noNetWork:
                change(StartConntionWifiActivity.class,MainActivity.this,new Intent(),false);
                break;
            case R.id.rl_noTaoCan:
                change(DebitNoteActivity.class,MainActivity.this,new Intent(),false);
                break;
            case R.id.header_right_msg:
                change(DerviceItemActivity.class,MainActivity.this,new Intent(),false);
                break;
            case R.id.close_im_monery:
                rlNoTaoCan.setVisibility(View.GONE);
                break;
            case R.id.history_day:
                days="7";

                dayA=1;
                DayFlag_SET_ZOOM =  2;

                setSelect(0);

                setDayUpdate(days);


                break;
            case R.id.history_week:
                days="14";
                dayA=2;
                DayFlag_SET_ZOOM =  1;

                setSelect(1);
                setDayUpdate(days);



                break;
            case R.id.history_mouth:
                days="21";
                dayA=2;
                DayFlag_SET_ZOOM =  1;

                setSelect(2);
                setDayUpdate(days);



                break;
            case R.id.history_ji:
                days="28";
                dayA=2;
                DayFlag_SET_ZOOM =  1;
                setSelect(3);
                setDayUpdate(days);
                break;
        }
    }





    /**
     * 点击变色
     */
    void setSelect(int posotion) {
        //设置背景
        for (int i = 0; i < textViews.length; i++) {
            if (i == posotion) {
                if (0 == posotion) {
                    textViews[i].setBackgroundResource(R.drawable.music_select_left_bg);
                } else if (textViews.length - 1 == posotion) {
                    textViews[i].setBackgroundResource(R.drawable.music_select_right_bg);
                } else {
                    textViews[i].setBackgroundColor(0xffffffff);
                }
            } else {
                textViews[i].setBackgroundColor(0x00000000);
            }
        }
        //设置字体颜色
        for (int i = 0; i < textViews.length; i++) {
            if (i == posotion) {
                textViews[i].setTextColor(Color.parseColor("#249DFF"));
            } else {
                textViews[i].setTextColor(0xffffffff);
            }
        }


    }


    /**
     * 请求返回结果
     * **/

    UserBean userBean;

    DayDateBean dayDateBean;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson();
        if (type==0x01){

            userBean = gson.fromJson(sequest,UserBean.class);
            if (!TextUtils.isEmpty(userBean.getData().getHospitalId())) {
                SPUtils.putString(MainActivity.this, "YID", userBean.getData().getHospitalId());
            }else {
                SPUtils.putString(MainActivity.this, "YID", "0");
            }
            if (!TextUtils.isEmpty(userBean.getData().getExpiryTime())) {
                SPUtils.putString(MainActivity.this, "DTIME", userBean.getData().getExpiryTime());
            }else {
                SPUtils.putString(MainActivity.this, "DTIME", "");
            }
            Intent intent =new Intent("AHeader");
            if (!TextUtils.isEmpty(userBean.getData().getHeadUrl())) {
                intent.putExtra("header", userBean.getData().getHeadUrl());
            }
            LogUtils.e("---->"+userBean.getData().getHeadUrl());
            intent.putExtra("name",userBean.getData().getName());
            LogUtils.e("---->"+userBean.getData().getName());
            intent.putExtra("shenfen",userBean.getData().getAccount());
            LogUtils.e("---->"+userBean.getData().getAccount());

//            Log.d("---->传的图片",userBean.getData().getHeadUrl());
            sendBroadcast(intent);
            if (userBean.getData().getStatus().contains("1")){

                handler.sendEmptyMessage(3);
            }else {

                handler.sendEmptyMessage(4);
            }
            if (userBean.getData().getOnline().contains("1")){

                handler.sendEmptyMessage(1);
            }else {

                handler.sendEmptyMessage(2);
            }
            if (userBean.getData().getStatus().contains("0") && userBean.getData().getOnline().contains("2") ){

                handler.sendEmptyMessage(10);

            }



        }else if (type==0x20){

            Log.e("----->1","1");

            dayDateBean =gson.fromJson(sequest,DayDateBean.class);
            if (dayDateBean.getCode()!=3001) {
                Log.e("----->1", "1" + dayDateBean.getData().toString());
                listIntOne.clear();
                listIntOneXstr.clear();
                listIntOneXstrCopy.clear();
                listIntTwo.clear();
                listIntTwoXstr.clear();
                listIntThree.clear();
                listIntThreeXstr.clear();
                listIntFour.clear();
                listIntFive.clear();
                listIntSix.clear();
                listIntFourXstr.clear();

                Log.e("----->2", "2");
                for (int i = 0; i < dayDateBean.getData().getEquDatas().size(); i++) {
                    //第一个图库所有的值
                    listIntOne.add(dayDateBean.getData().getEquDatas().get(i).getFluid());
                    listIntTwo.add(dayDateBean.getData().getEquDatas().get(i).getUrineVolume());
                    listIntThree.add(dayDateBean.getData().getEquDatas().get(i).getWeight());
                    listIntFour.add(dayDateBean.getData().getEquDatas().get(i).getBloodPressureHeight());
                    listIntFive.add(dayDateBean.getData().getEquDatas().get(i).getBloodPressureLow());
                    listIntSix.add(dayDateBean.getData().getEquDatas().get(i).getHeartRate());
                    if (!TextUtils.isEmpty(dayDateBean.getData().getEquDatas().get(i).getCreatetime())) {
                        listIntOneXstr.add(Util.replaceAll(i, dayDateBean.getData().getEquDatas().get(i).getCreatetime()) + "\n" + " " + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeek());
                        listIntOneXstrCopy.add( dayDateBean.getData().getEquDatas().get(i).getCreatetime() + "\n" + " " + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeek());

                    }
                    if (!TextUtils.isEmpty(dayDateBean.getData().getEquDatas().get(i).getVolumeDate())) {
                        listIntTwoXstr.add(Util.replaceAll(i, dayDateBean.getData().getEquDatas().get(i).getVolumeDate()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getVolumeDate().substring(11, dayDateBean.getData().getEquDatas().get(i).getVolumeDate().length()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeek());
                    }
                    if (!TextUtils.isEmpty(dayDateBean.getData().getEquDatas().get(i).getWeightDate())) {
                        listIntThreeXstr.add(Util.replaceAll(i, dayDateBean.getData().getEquDatas().get(i).getWeightDate()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeightDate().substring(11, dayDateBean.getData().getEquDatas().get(i).getWeightDate().length()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeek());
                    }
                    if (!TextUtils.isEmpty(dayDateBean.getData().getEquDatas().get(i).getBloodHeartDate())) {
                        listIntFourXstr.add(Util.replaceAll(i, dayDateBean.getData().getEquDatas().get(i).getBloodHeartDate()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getBloodHeartDate().substring(11, dayDateBean.getData().getEquDatas().get(i).getBloodHeartDate().length()) + "\n" + dayDateBean.getData().getEquDatas().get(i).getWeek());
                    }
                }
                Log.e("----->3", "3");
                handler.sendEmptyMessage(5);
            }
        }

    }

    @Override
    public void onFailure(String exp) {

    }

    @Override
    public void notifiy() {


    }


    /**
     *  相机相册测回调
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // 相册返回
        if (PhotoUtil.CAMRA_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                // 相册选中图片路径
                String cameraPath = photoUtil.getCameraPath(data);
                Bitmap bitmap = photoUtil.readBitmapAutoSize(cameraPath);
                LogUtils.d("相相册选中路径  = " + cameraPath);
                startClipActivity(cameraPath);
            }
        }
        // 相机返回
        else if (PhotoUtil.PHOTO_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                String photoPath = photoUtil.getPhotoPath();
                Bitmap bitmap = photoUtil.readBitmapAutoSize(photoPath);
                LogUtils.d("相机选中路径  = " + photoPath);
                startClipActivity(photoPath);

            }
        }
        // 裁剪返回
        else if (PhotoUtil.PHOTO_CORPRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                LogUtils.d("裁剪返回  = ");
                String path = data.getStringExtra("path");

                /**
                 *  //去上传图片
                 * */
                File file =new File(path);
                Log.i("--->pathss",path);
//                okHttpUpload("pic",file);

                Map<String,Object> maps =new HashMap<>();
                maps.put("id",SPUtils.getInt(MainActivity.this,MyConstaints.USER_ID,0));
                okHttpForPicture(maps,file);

            }
        }

    }



    //点击跳转到图片处理的界面
    public void startClipActivity(String path) {
        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, PhotoUtil.PHOTO_CORPRESULT_CODE);
    }




    private BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();
            if (action.contains("Header")){

                photoUtil = new PhotoUtil(MainActivity.this);
                photoUtil.showDialog("图册","拍照");
            }
        }
    };



    /**
     *  头像上传
     * */
    String  header;
    private void okHttpForPicture( final Map<String, Object> map, File file) {

        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(StringUtils.HTTP_SERVICE+StringUtils.HEARDER).post(requestBody.build()).tag(MainActivity.this).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("lfq" ,"onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    Log.i("--->成功上传图片", response.message() + " , body " + str);

                    try {
                        JSONObject object = new JSONObject(str);
                        header = object.getString("data");
                        handler.sendEmptyMessage(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.i("--->上传图片失败" ,response.message() + " error : body " + response.body().string());
                }
            }
        });
    }





    private void  OneLinChart( int days,List<String>lists,LineChart lineChart_new,List<Integer>listsInt,int color,int type,int flag ,int MAXANDMIN) {

        String[] valuesStr = lists.toArray(new String[lists.size()]);

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < listsInt.size(); i++) {
            values.add(new Entry(i, listsInt.get(i)));
        }
        if (days == 1) {
            WZ_lineChartCopy.getInstance().initChartView(lineChart_new, valuesStr,MAXANDMIN);
            WZ_lineChartCopy.getInstance().initDataDan(values, this, lineChart_new, color, type, flag);

        } else {
            WZ_lineChart.getInstance().initChartView(lineChart_new, valuesStr,MAXANDMIN);
            WZ_lineChart.getInstance().initDataDan(values, this, lineChart_new, color, type, flag);
        }
    }
//    private void  OneLinChart( int days,List<String>lists,LineChart lineChart_new,List<Integer>listsInt,int color,int type,int flag ) {
//
//        String[] valuesStr = lists.toArray(new String[lists.size()]);
//
//        ArrayList<Entry> values = new ArrayList<>();
//        for (int i = 0; i < listsInt.size(); i++) {
//            values.add(new Entry(i, listsInt.get(i)));
//        }
//        if (days == 1) {
//            NewWZ_lineChat.getInstance().initChartView(lineChart_new,valuesStr);
//            NewWZ_lineChat.getInstance().notifyDataSetChanged(lineChart_new,MainActivity.this,values,valuesStr,color,type);
////            WZ_lineChartCopy.getInstance().initDataDan(values, this, lineChart_new, color, type, flag);
//
//        } else {
////            WZ_lineChart.getInstance().initChartView(lineChart_new, valuesStr);
//            NewWZ_lineChat.getInstance().initChartView(lineChart_new,valuesStr);
//            NewWZ_lineChat.getInstance().notifyDataSetChanged(lineChart_new,MainActivity.this,values,valuesStr,color,type);
////            WZ_lineChart.getInstance().initDataDan(values, this, lineChart_new, color, type, flag);
//        }
//    }
    private void  OneLinChartDouble( int days,List<String>lists,LineChart lineChart_new,List<Integer>listsInt1,List<Integer>listsInt2,List<Integer>listsInt3,int flag,int MAXANDMIN) {

        String[] valuesStr = lists.toArray(new String[lists.size()]);

        ArrayList<Entry> values1 = new ArrayList<>();
        for (int i = 0; i < listsInt1.size(); i++) {
            values1.add(new Entry(i, listsInt1.get(i)));
        }
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < listsInt2.size(); i++) {
            values2.add(new Entry(i, listsInt2.get(i)));
        }
        ArrayList<Entry> values3 = new ArrayList<>();
        for (int i = 0; i < listsInt3.size(); i++) {
            values3.add(new Entry(i, listsInt3.get(i)));
        }
        if (days == 1) {

            WZ_lineChartCopy.getInstance().initChartView(lineChart_new, valuesStr,MAXANDMIN);
            WZ_lineChartCopy.getInstance().initData(values1, values2, values3, this, lineChart_new, flag);

        } else {
            WZ_lineChart.getInstance().initChartView(lineChart_new, valuesStr,MAXANDMIN);
            WZ_lineChart.getInstance().initData(values1, values2, values3, this, lineChart_new, flag);
        }
    }


    private void setDayUpdate(String days){
        Map<String ,String>maps =new ArrayMap<>();
        maps.put("time",days);
        maps.put("userId",SPUtils.getInt(this, MyConstaints.USER_ID,0)+"");
//        maps.put("userId","4");
        BigModle.getInstance(this).getData(MainActivity.this,maps,0x20,this,StringUtils.DAY_DATE);


    }

    private static  List<String>listIntent;
    private static  List<String>listIntentCOPY;
    public  static  List<String>  getLi9st(){

        return listIntent;
    }
    public  static  List<String>  getLi9stCOPY(){

        return listIntentCOPY;
    }

}
