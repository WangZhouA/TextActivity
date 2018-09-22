package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */
public class DebitNoteActivity extends BaseActivity implements HttpRequestCallback{
    @BindView(R.id.header_left)
    ImageButton headerLeft;



    @BindView(R.id.header_text)
    TextView headerText;
    @BindView(R.id.rl_shopping_cart)
    RelativeLayout rlShoppingCart;
    @BindView(R.id.rl_detail)
    RelativeLayout rlDetail;

    @BindView(R.id.money)
    TextView moneyTv;

    @BindView(R.id.tv_daoqi)
    TextView tvDaoqi;


    private Handler mHandler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            moneyTv.setText(monery);
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activty_debitnote);
    }

    @Override
    protected void initData() {
        headerText.setText(getResources().getString(R.string.debit_note));
        if (!TextUtils.isEmpty(SPUtils.getString(DebitNoteActivity.this, "DTIME", ""))) {
            tvDaoqi.setText(SPUtils.getString(DebitNoteActivity.this, "DTIME", ""));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.getInstance(this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.MONERY+ SPUtils.getInt(this, MyConstaints.USER_ID,0),this);

    }

    @OnClick({R.id.header_left, R.id.rl_shopping_cart, R.id.rl_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_left:
                finish(DebitNoteActivity.this);
                break;
            case R.id.rl_shopping_cart:

                change(BuyActivity.class,DebitNoteActivity.this,new Intent(),false);

                break;
            case R.id.rl_detail:
                change(OrderDetailsActivity.class,DebitNoteActivity.this,new Intent(),false);
                break;
        }
    }
    String monery="";
    @Override
    public void onResponse(String sequest, int type) {

        try {
            JSONObject jsonObject =new JSONObject(sequest);

            if (type==0x01){

                monery =jsonObject.getString("data");

                mHandler.sendEmptyMessage(0);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(String exp) {

    }
}
