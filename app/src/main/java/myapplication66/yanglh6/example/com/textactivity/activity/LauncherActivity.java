package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import myapplication66.yanglh6.example.com.textactivity.MainActivity;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.LoginBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

public class LauncherActivity extends BaseActivity implements HttpRequestCallback {



    @Override
    protected void initView() {
        setContentView(R.layout.activity_launcher);
    }

    @Override
    protected void initData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!TextUtils.isEmpty(SPUtils.getString(LauncherActivity.this,MyConstaints.LOGIN_USERNAME,""))) {
                    setBtnLogin();
                }else {
                    change(LoginActivity.class, LauncherActivity.this, new Intent(), true);

                }
            }
        },3000);
    }

    private void setBtnLogin(){

        if (isNetworkAvalible(LauncherActivity.this)) {
                Map<String, String> maps = new ArrayMap<>();
                maps.put("account", SPUtils.getString(LauncherActivity.this,MyConstaints.LOGIN_USERNAME,""));
                maps.put("password",SPUtils.getString(LauncherActivity.this,MyConstaints.LOGIN_USERPWD,""));
                BigModle.getInstance(LauncherActivity.this).getData(LauncherActivity.this, maps, 0x01, this, StringUtils.LOGIN);

                //
//                        } else {
//                            toast( getResources().getString(R.string.Please_check_whether_the_account_password_is_correct));
//
//                        }
        }else {
            toast(getResources().getString(R.string.not_networks_conntion));
            change(LoginActivity.class,LauncherActivity.this,new Intent().putExtra("LOUT","2"),false);

        }

    }

    LoginBean loginBean;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson();
        loginBean = gson.fromJson(sequest,LoginBean.class);
        if (type==0x01) {


            if (loginBean.isSuccess() == true) {

                SPUtils.putString(LauncherActivity.this, MyConstaints.TOKEN, loginBean.getData().getToken());
                SPUtils.putInt(LauncherActivity.this, MyConstaints.USER_ID, loginBean.getData().getId());
                JPushInterface.setAlias(LauncherActivity.this, SPUtils.getString(LauncherActivity.this, MyConstaints.LOGIN_USERNAME, ""), new TagAliasCallback() {
                    @Override
                    public void gotResult(int requestCode, String s, Set<String> set) {
                        int requestCodeA = requestCode;
                        Log.e("requestCodeA---------->", "====" + requestCodeA);
                        switch (requestCode) {
                            case 0:
                                Log.e("别名设置成功---------------->", "====" + s);
                                break;
                            case 6002:
                                Log.e("别名设置失败---------------->", "====" + s);
                                break;
                        }
                    }
                });
                change(MainActivity.class,LauncherActivity.this,new Intent(),true);
            }else {

                change(LoginActivity.class,LauncherActivity.this,new Intent(),true);

            }
        }
    }


    @Override
    public void onFailure(String exp) {

    }
}
