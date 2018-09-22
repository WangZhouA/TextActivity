package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;


import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.BillingDetailsBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.LogUtils;

/**
 * Created by 陈姣姣 on 2018/6/29.
 */
public class BillingDetailsActivity extends BaseActivity implements HttpRequestCallback{


    @BindView(R.id.header_all)
    LinearLayout rlAll;

    @BindView(R.id.header_text)
    TextView textText;
    @BindView(R.id.tv_billingDetails)
    TextView tvBillingDetails;
    @BindView(R.id.tv_Terms_of_payment)
    TextView tvTermsOfPayment;
    @BindView(R.id.tv_Transaction_amount)
    TextView tvTransactionAmount;
    @BindView(R.id.tv_Creation_time)
    TextView tvCreationTime;
    @BindView(R.id.tv_The_order_number)
    TextView tvTheOrderNumber;
    @BindView(R.id.tv_TransactionTitle)
    TextView tvTransactionTitle;
    @BindView(R.id.tv_Transaction)
    TextView tvTransaction;
    @BindView(R.id.tv_View_electronic_invoice)
    TextView tvViewElectronicInvoice;

    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {

            tvBillingDetails.setText(billingDetailsBean.getData().getSubject());
            tvTermsOfPayment.setText(billingDetailsBean.getData().getPaymentMethod());
            tvTransactionAmount.setText(billingDetailsBean.getData().getAmount());
            tvTheOrderNumber.setText(billingDetailsBean.getData().getOuttradeNo());
            tvCreationTime.setText(billingDetailsBean.getData().getExpiryTime());
            tvTransactionTitle.setText(billingDetailsBean.getData().getPaymentMethod()+getResources().getString(R.string.Transaction));
            tvBillingDetails.setText(billingDetailsBean.getData().getSubject());
            tvTransaction.setText(billingDetailsBean.getData().getTradeno() );
        }
    };

    @Override
    protected void initView() {

        setContentView(R.layout.activity_billing_detail);
    }

    @Override
    protected void initData() {

        showNormeBar();
        backImageView(R.mipmap.back_b);
        textText.setTextColor(Color.parseColor("#242424"));
        textText.setText(getResources().getString(R.string.billingDetails));
        //设置沉浮状态栏 flag 是否沉浮__适用于滑动的viewpager  true为沉浮
        setWindowfullScreen(true);
        //系统状态栏颜色
        setWindowStatusBarColor(Color.parseColor("#FFFFFF"));
        rlAll.setBackgroundColor(Color.parseColor("#FFFFFF"));


        Intent intent =getIntent();
        String number= intent.getStringExtra( "num");
        LogUtils.e("=="+StringUtils.HTTP_SERVICE+StringUtils.ZHANGDAN_XIANGQING+number);
        HttpUtils.getInstance(BillingDetailsActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.ZHANGDAN_XIANGQING+number ,this);


    }


    @OnClick(R.id.tv_View_electronic_invoice)
    public void onViewClicked() {

        toast("暂未开放");
    }

    BillingDetailsBean  billingDetailsBean;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson() ;
        billingDetailsBean= gson.fromJson(sequest,BillingDetailsBean.class);
        handler.sendEmptyMessage(0);

    }

    @Override
    public void onFailure(String exp) {

    }
}
