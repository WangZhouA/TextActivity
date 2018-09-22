package myapplication66.yanglh6.example.com.textactivity.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.saiyi.library.XPermissionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import myapplication66.yanglh6.example.com.textactivity.MyApplication;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.UserInfo;
import myapplication66.yanglh6.example.com.textactivity.utils.Functions;

/**
 * Created by 陈姣姣 on 2018/6/12.
 */
public abstract   class  BaseActivity extends XPermissionActivity{
    //加载框
    LoadingDailog dialog;
  public   UserInfo userInfo;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView();
        ButterKnife.bind(this);
        //设置沉浮状态栏 flag 是否沉浮__适用于滑动的viewpager  true为沉浮
        setWindowfullScreen(true);
        //系统状态栏颜色
        setWindowStatusBarColor(Color.parseColor("#20A0FF"));
        //把每一个activity都放进一个数组便于退出
        MyApplication.getInstance().addActyToList(this);
        userInfo =new UserInfo(this);
        JPushInterface.init(getApplicationContext());
        // 初始化所有的控件绑定

        if (checkNet() == true) {

        } else {
            Toast.makeText(this, getResources().getString(R.string.not_networks_conntion), Toast.LENGTH_SHORT).show();
        }

        // 隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        /**
         *  请求权限
         * */
        checkAndroidMPermission();
        initData();

    }


    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();



    /**
     *
     *    方法二： 沉浸栏
     * */
    //******************************************************************************************************

    //系统状态栏颜色
    public void setWindowStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Functions.setWindowStatusBarColor(this, color);
        }
    }

    //设置沉浮状态栏 flag 是否沉浮__适用于滑动的viewpager  true为沉浮
    public void setWindowfullScreen(boolean flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setStatusBarTextColor(flag);
    }

    //改变状态栏字体颜色 false为白色___true为黑色
    void setStatusBarTextColor(boolean textFlag) {
        Functions.setStatusBarTextColor(this, textFlag);
    }





    //******************************************************************************************************

    /**
     *
     *    方法一： 用于点击外部隐藏键盘实用性很高
     * */
    //******************************************************************************************************

    /**
     * 对于所有的Activity都适用,所以定义在BaseActivity里面,被子类继承
     * 点击外部隐藏输入软键盘,获取到EditText的位置,做出点击判断
     */
    public boolean isClickEditText(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的 location 位置
            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            // 此处根据输入框左上位置和宽高获得右下位置
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }


    /**
     * 分发点击事件.点击外部键盘消失
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 获取当前获得当前焦点所在View
            View view = getCurrentFocus();
            if (isClickEditText(view, event)) {
                // 如果不是edittext，则隐藏键盘
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    // 隐藏键盘
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(event);
        }
        /**
         * 看源码可知superDispatchTouchEvent 是个抽象方法，用于自定义的Window
         * 此处目的是为了继续将事件由dispatchTouchEvent (MotionEvent event) 传递到onTouchEvent
         * (MotionEvent event) 必不可少，否则所有组件都不能触发 onTouchEvent (MotionEvent event)
         */
        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }
        return onTouchEvent(event);
    }
    //******************************************************************************************************

    /**
     *
     *   finsh  当前页面
     * */
    public void finish(Activity activity) {
        super.finish();
        MyApplication.getInstance().removeActyFromList(activity);
    }

    /**
     * 判断当前的网络环境
     */
    public boolean checkNet() {
        return Functions.getNetworkState(getApplicationContext()) != Functions.NETWORN_NONE;
    }

    /**
     *  方法三，用于弹框提示
     * */
//***********************************************************************
    public void toast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public void toast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //***********************************************************************



    /**
     *
     * 方法四，activity跳转
     * */
    //***********************************************************************
    /**
     * @param cls        现在activity
     * @param activity   现在activity
     * @param intent     有参数和无参数intent
     * @param isFinished 是否销毁当前activity
     */
    public void change(Class<? extends Activity> cls, Activity activity, Intent intent, boolean isFinished) {
        intent.setClass(activity, cls);
        startActivity(intent);
        if (isFinished) {
            finish(activity);
        }
    }

    public void changeForResult(Class<? extends Activity> cls, Activity activity, Intent intent, int i, boolean isFinished) {
        intent.setClass(activity,cls);
        activity.startActivityForResult(intent, i);
        if (isFinished) {
            finish(activity);
        }
    }

    //***********************************************************************


    /**
     *  方法五  头部的初始化
     * */

    //***********************************************************************

    //设置标题
    public void setTile(String title) {
        TextView view = (TextView) findViewById(R.id.header_text);
        if (view == null) return;
        view.setText(title);
    }

    //返回键隐藏
    public void backGons() {
        View view = findViewById(R.id.header_left);
        if (view == null) return;
        view.setVisibility(View.GONE);
    }
    //返回键改变样式
    public void backImageView(int ResouseId) {
        ImageButton view = findViewById(R.id.header_left);
        if (view == null) return;
        view.setImageResource(ResouseId);
    }

    /**
     * 一般自定义actionBar  返回键
     */
    public void showNormeBar() {
        View view = findViewById(R.id.header_left);
        if (view == null) return;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    //***********************************************************************


 /**
  *      方法六 加载框显示
  * */

 //***********************************************************************
    public  void  dialogShow(String text){
        LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(this)
                .setMessage(text)
                .setCancelable(true)
                .setCancelOutside(true);
        dialog=loadBuilder.create();
        dialog.show();


    }

    public  void  disDialog(){
        dialog.dismiss();


    }
//***********************************************************************
 /**
  *  用于加碎片页面  fragment
  * */

//    protected void replace(int layoutId, Fragment f, String tag) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(layoutId, f, tag);
//        ft.commit();
//    }
/**
 *    动态获取权限
 * */
    public static List<String> permission(Context context, String[] permissions) {
        List<String> permissionList = new ArrayList<String>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                //ActivityCompat.requestPermissions(activity, new String[]{permissions[i]}, 10);//自定义的code
                permissionList.add(permissions[i]);
            }
        }
        return permissionList;
    }

    //权限申请的方法
    public void permissionUtis(Context context, Activity activity, String permissions, int permssType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context, permissions) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permissions}, permssType);//自定义的code
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permissions}, permssType);//自定义的code
        }
    }

    private void checkAndroidMPermission(){
        requestPermission(new String[]{
                Manifest.permission.CAMERA,//相机权限
                Manifest.permission.WRITE_EXTERNAL_STORAGE,//SD卡图库权限
//                Manifest.permission.ACCESS_FINE_LOCATION//定位权限
        }, new PermissionHandler() {
            @Override
            public void onGranted() {
                Log.e("----??","3");
            }


            @Override
            public void onDenied() {
                Log.e("----??","2");
                super.onDenied();
            }

            @Override
            public boolean onNeverAsk() {
                Log.e("----??","1");
                return super.onNeverAsk();
            }
        });
    }


    public  boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }










}
