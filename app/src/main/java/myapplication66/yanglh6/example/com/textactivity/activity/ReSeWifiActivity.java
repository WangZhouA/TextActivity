package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.utils.WifiUtils;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */

/**
 * Created by 陈姣姣 on 2018/6/22.
 */
public class ReSeWifiActivity extends BaseActivity implements TextWatcher {


    @BindView(R.id.link_wifi_text_name)
    TextView tvWifName;

    @BindView(R.id.link_wifi__pas)
    EditText edWifiPassWord;


    @BindView(R.id.link_wifi_eyes)
    ImageButton imBtnEyes;

    @BindView(R.id.link_btn)
    Button btnNext;

    WifiUtils wifiUtils;

    //密文显示标识符
    int flag;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_reset_connection);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.connection_Wifi));

        edWifiPassWord.addTextChangedListener(this);
        wifiUtils=new WifiUtils(this);
        //密文显示
        if (flag == 0) {
            edWifiPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
            flag = 1;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvWifName.setText(wifiUtils.getWifiConnectedSsid());

        if (!TextUtils.isEmpty(userInfo.getStringInfo("wifiPass"))){

            edWifiPassWord.setText(userInfo.getStringInfo("wifiPass"));
        }

    }

    @OnClick({  R.id.link_wifi_eyes, R.id.link_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.link_wifi_eyes:
                if (flag == 1) {
                    edWifiPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = 0;
                } else {
                    edWifiPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = 1;
                }

                break;
            case R.id.link_btn:

                userInfo.setUserInfo("wifiPass", edWifiPassWord.getText().toString());
                if (!TextUtils.isEmpty(tvWifName.getText().toString()) && !TextUtils.isEmpty(edWifiPassWord.getText().toString())) {
                    change(ConnectioningWifiActivity.class,ReSeWifiActivity.this,new Intent().putExtra("WifiName", tvWifName.getText().toString()).
                            putExtra("WifiPass", edWifiPassWord.getText().toString()),true);
                }
                break;
        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        //1.同时监听俩个输入框，监听长度
        if ( edWifiPassWord.length() >= 6) {
            btnNext.setEnabled(true);
            btnNext.setBackgroundResource(R.drawable.btn_xiaogou);
        } else {
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_xiaogou_hui);
        }
    }
}
