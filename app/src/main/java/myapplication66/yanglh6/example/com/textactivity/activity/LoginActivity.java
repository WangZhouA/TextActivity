package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import myapplication66.yanglh6.example.com.textactivity.MainActivity;
import myapplication66.yanglh6.example.com.textactivity.MyApplication;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.LoginBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.MyDialog;

/**
 * Created by 陈姣姣 on 2018/6/15.
 */
public class LoginActivity extends BaseActivity implements HttpRequestCallback{
    @BindView(R.id.et_identity_card)
    EditText etIdentityCard;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_clear_pwd)
    ImageView ivClearPwd;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.btn_login)
    Button btnLogin;


    String str;

    //密文显示标识符
    int  flag = 0;


    private int  LOGIN = 0x01;


    private int showDialogFlag=0;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() {

        //密文显示
        if (flag == 0) {
            etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            flag = 1;
        }



        /**
         * 限制身份证的验证字符
         * */
        etIdentityCard.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return android.text.InputType.TYPE_CLASS_PHONE;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X' };
                return numberChars;
            }
        });


//
//        etPwd.setText("1234567");
//        etIdentityCard.setText( ("421023199309127590"));
//
//



    }





    @OnClick({R.id.iv_clear, R.id.iv_clear_pwd, R.id.iv_eye, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                str =etIdentityCard.getText().toString();
                if (str.length()>0) {
                    etIdentityCard.setText("");
                }else {

                }
                break;
            case R.id.iv_clear_pwd:
                str =etPwd.getText().toString();
                if (str.length()>0) {
                    etPwd.setText("");
//                    etPwd.setText(str.substring(0, str.length() - 1));
//                    etPwd.setSelection(str.length() - 1);
                }
                break;
            case R.id.iv_eye:

                if (flag == 1) {
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = 0;
                } else {
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = 1;
                }


                break;
            case R.id.btn_login:

                setBtnLogin();

                break;

        }
    }
    private void setBtnLogin(){


        if (isNetworkAvalible(LoginActivity.this)) {
            if (etPwd.getText().toString().length() >= 6) {
                //    if (IDCard.IDCardValidate(etIdentityCard.getText().toString()) == true) {
                SPUtils.putString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, etIdentityCard.getText().toString());
                SPUtils.putString(LoginActivity.this, MyConstaints.LOGIN_USERPWD, etPwd.getText().toString());

                Map<String, String> maps = new ArrayMap<>();
                maps.put("account", etIdentityCard.getText().toString());
                maps.put("password", etPwd.getText().toString());
                BigModle.getInstance(LoginActivity.this).getData(LoginActivity.this, maps, LOGIN, this, StringUtils.LOGIN);

                //
//                        } else {
//                            toast( getResources().getString(R.string.Please_check_whether_the_account_password_is_correct));
//
//                        }
            } else {
                toast(getResources().getString(R.string.password_leng_erro));
            }
        }else {
            toast(getResources().getString(R.string.not_networks_conntion));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

/**
 *  修改密码跳进来的
 * */
        Intent intent =getIntent();
        if (!TextUtils.isEmpty(intent.getStringExtra("MM"))) {

            if (!TextUtils.isEmpty(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""))) {
                etIdentityCard.setText(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""));
                etPwd.setText(intent.getStringExtra("MM"));

            }


/**
 *  正常登录进来
 * */
        }  else if (!TextUtils.isEmpty(intent.getStringExtra("LOUT"))) {

            if (!TextUtils.isEmpty(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""))) {
                etIdentityCard.setText(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""));
//                etPwd.setText(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERPWD, ""));

            }


        } else {

            if (!TextUtils.isEmpty(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""))) {
                showDialogFlag=1;
                etIdentityCard.setText(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""));
                etPwd.setText(SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERPWD, ""));
                setBtnLogin();
            }else {
                showDialogFlag=0;
            }
        }

    }

    LoginBean loginBean;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson();
        loginBean = gson.fromJson(sequest,LoginBean.class);
        if (type==LOGIN){
            if (loginBean.isSuccess()==true) {

                SPUtils.putString(LoginActivity.this, MyConstaints.TOKEN, loginBean.getData().getToken());
                SPUtils.putInt(LoginActivity.this, MyConstaints.USER_ID, loginBean.getData().getId());
                JPushInterface.setAlias(LoginActivity.this, SPUtils.getString(LoginActivity.this, MyConstaints.LOGIN_USERNAME, ""),new TagAliasCallback() {
                    @Override
                    public void gotResult(int requestCode, String s, Set<String> set) {
                        int  requestCodeA= requestCode;
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
                if (showDialogFlag==0) {
                    showDialog();
                }else {
                    change(MainActivity.class, LoginActivity.this, new Intent(), true);
                }



            }
        }

    }

    @Override
    public void onFailure(String exp) {

        LoginErros();

    }
    MyDialog  myUser_Dialog;
    private  void  showDialog(){

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_user_item,null);
        if (myUser_Dialog==null) {
            myUser_Dialog = new MyDialog(this, 0, 0, view, R.style.MyDialog);
        }
        TextView tvNo = view.findViewById(R.id.tv_no);
        TextView tvYes = view.findViewById(R.id.tv_yes);

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myUser_Dialog.dismiss();

            }
        });
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                change(MainActivity.class, LoginActivity.this, new Intent(), true);
                myUser_Dialog.dismiss();

            }
        });

        myUser_Dialog.show();



    }
    MyDialog myDialog;
    private  void  LoginErros() {

        View view = LayoutInflater.from(this).inflate(R.layout.qiangzhi_xiaxian_layout, null);
        if (myDialog==null) {
            myDialog = new MyDialog(this, 0, 0, view, R.style.MyDialog);
        }
        TextView tvNo = view.findViewById(R.id.btn_yes_xian_ss);
        TextView tvYes = view.findViewById(R.id.btn_no_xiaxian_ss);

        TextView title_tv = view.findViewById(R.id.for_tv_titles);
        TextView title_msg = view.findViewById(R.id.tv_msg);
        title_msg.setTextColor(Color.parseColor("#242424"));
        title_tv.setText(getResources().getString(R.string.login_erro));
        title_msg.setText(getResources().getString(R.string.login_erro_msg));
        tvNo.setText(getResources().getString(R.string.makesure));
        tvYes.setVisibility(View.GONE);

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

            }
        });
        myDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myDialog != null && myDialog.isShowing())
        {
            myDialog.dismiss();
        }

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




}
