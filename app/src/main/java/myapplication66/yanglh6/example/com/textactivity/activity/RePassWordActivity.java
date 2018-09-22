package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

public class RePassWordActivity extends BaseActivity implements HttpRequestCallback{


    @BindView(R.id.header_left)
    ImageButton headerLeft;
    @BindView(R.id.header_text)
    TextView headerText;
    @BindView(R.id.tv_old)
    EditText tvOld;
    @BindView(R.id.tv_new)
    EditText tvNew;
    @BindView(R.id.tv_new_newpass)
    EditText tvNewNewpass;
    @BindView(R.id.btn_repass)
    Button btnRepass;


    private Handler handler =new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case 0:

                    toast(getResources().getString(R.string.old_pass_syccess));

                    change(LoginActivity.class,RePassWordActivity .this,new Intent().putExtra("MM",tvNewNewpass.getText().toString()),true);

                    break;

                case 1:
                            toast(getResources().getString(R.string.old_pass_erro));
                    break;
            }


        }
    };
    @Override
    protected void initView() {
        setContentView(R.layout.activity_re_pass_word);
    }

    @Override
    protected void initData() {

        headerText.setText(getResources().getString(R.string.re_user_name));
        tvNew.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);// 设置文本类密码（默认不可见），这两个属性必须同时设置
        tvNewNewpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);// 设置文本类密码（默认不可见），这两个属性必须同时设置

    }



    @OnClick({R.id.header_left, R.id.btn_repass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_left:

                finish(RePassWordActivity.this);

                break;

            case R.id.btn_repass:

                if (!TextUtils.isEmpty(tvOld.getText().toString().trim())){

                    if (!TextUtils.isEmpty(tvNew.getText().toString().trim())){

                        if (tvNew.getText().toString().length()>=6) {

                            if (!TextUtils.isEmpty(tvNewNewpass.getText().toString().trim())) {


                                if (tvNew.getText().toString().trim().equals(tvNewNewpass.getText().toString().trim())) {

                                    Map<String, String> maps = new ArrayMap<>();
                                    maps.put("account", SPUtils.getString(RePassWordActivity.this, MyConstaints.LOGIN_USERNAME, ""));
                                    maps.put("oldcipher", tvOld.getText().toString());
                                    maps.put("password", tvNewNewpass.getText().toString());
                                    BigModle.getInstance(RePassWordActivity.this).getData(RePassWordActivity.this, maps, 0X01, this, StringUtils.REPASSWORD);

                                } else {

                                    toast(getResources().getString(R.string.pass_no_yizhi));
                                }

                            } else {

                                toast(getResources().getString(R.string.pass_no_null));
                            }


                        }else {
                            toast(getResources().getString(R.string.changdu));
                        }

                    }else {
                        toast(getResources().getString(R.string.pass_no_null));
                    }

                }else {

                    toast(getResources().getString(R.string.pass_no_null));
                }



                break;
        }
    }

    @Override
    public void onResponse(String sequest, int type) {

        try {
            JSONObject object =new JSONObject(sequest);
            if (object.getInt("code")==1000){

                handler.sendEmptyMessage(0);

            }else if (object.getInt("code")==2002){


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(String exp) {
        handler.sendEmptyMessage(1);
    }
}
