package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MoneryBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.WXBean;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.PayResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */
public class BuyActivity extends BaseActivity  implements HttpRequestCallback {
    @BindView(R.id.header_left)
    ImageButton headerLeft;
    @BindView(R.id.header_left_text)
    TextView headerLeftText;
    @BindView(R.id.header_text)
    TextView headerText;
    @BindView(R.id.header_haoyou)
    ImageButton headerHaoyou;
    @BindView(R.id.header_right)
    ImageButton headerRight;
    @BindView(R.id.header_right_msg)
    TextView headerRightMsg;
    @BindView(R.id.header_all)
    LinearLayout headerAll;
    @BindView(R.id.buy_one)
    ImageView buyOne;
    @BindView(R.id.by_two)
    ImageView byTwo;
    @BindView(R.id.buy_Electronic_scale)
    ImageView buyElectronicScale;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.rl_zfb)
    RelativeLayout rlZfb;

    @BindView(R.id.tv_buy_one)
    TextView tv_buy_one;
    @BindView(R.id.tv_by_two)
    TextView tv_buy_two;

    private boolean wx_blooean;
    private boolean zfb_blooean;
    private int  xuanZe=1;

    @BindView(R.id.wx_no)
    ImageView imWX;
    @BindView(R.id.zfb_no )
    ImageView imZFB;

    @BindView(R.id.btn_buy)
    TextView tvBuy;


    private String  USERID="";
    private int buy_flag=1;



    double   money;
    String  subject;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_buy);


    }



    IWXAPI api;
    private Handler mHandler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:

//                    resultStatus={6001};memo={操作已经取消。};result={}
//                    resultStatus={9000};memo={};
// result={{"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2018020502148152",
// "auth_app_id":"2018020502148152","charset":"utf-8","timestamp":"2018-07-20 15:07:32","total_amount":
// "0.10","trade_no":"2018072021001004010579353545","seller_id":"2088921950347949","out_trade_no":"20180720150706429"},
// "sign":"lwP+CuW5S/XzVDGNBYhBigIWZ4K6Ap4bJi1usPtcP5GYm7Swhiznbo8JVMnSzVIUivk4P8sjuKruojUwjP56JGLrHmfwWj1+pCvPw4As8
// Unw/8flzo1D4Wnz8fPQZ64DN+ohijNqGQdqit7MeMBDl2Hs/9OQk+nGAZXvaRqDhRG3SMPN5vv+cdWlQXyfOy3fid06UbmesHZGcJuG6l7a6AH2YkRj63h
// Klx6ABin4bgxDSZl2sny0dQStbNBc6bVo0Vhky6Uc5UfNjOy/K/cfhy/80VBW3mZIRiuIKnQZ39Np0dZxZUq5ykFpSdCxJFzZXdFiThkLAII9cyKiyWtg9g==",
// "sign_type":"RSA2"}}
//                    resultStatus={6001};memo={用户取消};result={}
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                        change(BuyResultActvivity.class, BuyActivity.this, new Intent().putExtra("END",0), false);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                        change(BuyResultActvivity.class, BuyActivity.this, new Intent().putExtra("END",1), false);
                    }

                    break;

                case  1:

//     String timeStamp;
//    String packages;
//    String appId;
//    String sign;
//    String prepayid;
//    String partnerid;
//    String nonceStr;

                    StringUtils.APPID=appId;
                    api =  WXAPIFactory.createWXAPI(BuyActivity.this, "wxe1b1532d80eb2353");
                 if ( api.registerApp("wxe1b1532d80eb2353")==false){
                     Log.e("----->boolean","=="+api.registerApp("wxe1b1532d80eb2353"));
                 }else{
                     Log.e("----->boolean123","=="+api.registerApp("wxe1b1532d80eb2353"));
                 }
                    api.registerApp("wxe1b1532d80eb2353");
                    PayReq payRequest =new PayReq();
                    payRequest.appId="wxe1b1532d80eb2353";
                    Log.e("----->appId","=="+appId);
                    payRequest.partnerId=partnerid;
                    Log.e("----->partnerId","=="+partnerid);
                    payRequest.prepayId=prepayid;
                    Log.e("----->prepayId","=="+prepayid);
                    payRequest.packageValue=packages;
                    Log.e("----->packageValue","=="+packages);
                    payRequest.nonceStr=nonceStr;
                    Log.e("----->nonceStr","=="+nonceStr);
                    payRequest.timeStamp=timeStamp;
                    Log.e("----->timeStamp","=="+timeStamp);
                    payRequest.sign=sign;
                    Log.e("--->发起请求","开始");
                    Log.e("----->sign","=="+sign);
                    api.sendReq(payRequest);

                    break;

                case 2:

                    tv_buy_one.setText("￥"+sixMonths+"/6个月");
                    tv_buy_two.setText("￥"+twelvemonth+"/12个月");

                    break;


            }

        }
    };



    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.buy));


        toXuanZeBuy(buy_flag);
        toXuanZe(xuanZe);

        int  id = SPUtils.getInt(BuyActivity.this, MyConstaints.USER_ID, 0);
        USERID=String.valueOf(id);

        HttpUtils.getInstance(BuyActivity.this).getSyncHttp(0x10,StringUtils.HTTP_SERVICE+StringUtils.QUERY_MONERY,this);


    }


    @OnClick({R.id.buy_one, R.id.by_two, R.id.buy_Electronic_scale, R.id.rl_wx, R.id.rl_zfb,R.id.wx_no,R.id.zfb_no,R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buy_one:
                buy_flag=1;
                toXuanZeBuy(buy_flag);
                break;
            case R.id.by_two:
                buy_flag=2;
                toXuanZeBuy(buy_flag);
                break;
            case R.id.buy_Electronic_scale:
                break;
            case R.id.wx_no:

                if (wx_blooean == false) {
                    imWX.setImageResource(R.mipmap.success);
                    wx_blooean = true;
                    xuanZe=1;
                    toXuanZe(xuanZe);
                } else {
                    imWX.setImageResource(R.mipmap.circle);
                    wx_blooean = false;
                    xuanZe=2;
                    toXuanZe(xuanZe);
                }


                break;
            case R.id.zfb_no:
                if (zfb_blooean == false) {

                    imZFB.setImageResource(R.mipmap.success);
                    zfb_blooean = true;
                    xuanZe=2;
                    toXuanZe(xuanZe);

                } else {
                    imZFB.setImageResource(R.mipmap.circle);
                    zfb_blooean = false;
                    xuanZe=1;
                    toXuanZe(xuanZe);
                }

                break;
            case R.id.btn_buy:


                if (xuanZe==1){

                    if (buy_flag == 1) {

//                        money = 500;
                        money = 0.01;
                        subject = "6个月";

                    } else {

//                        money = 900;
                        money = 0.01;
                        subject = "12个月";
                    }


                    Map<String,String> map = new ArrayMap<>();
                    map.put("id",USERID);
                    map.put("totalfee",money+"");
                    BigModle.getInstance(BuyActivity.this).getData(BuyActivity.this,map,0x01,this,StringUtils.WXPAY);



                }else {


//                change(BuyResultActvivity.class, BuyActivity.this, new Intent(), false);
                    if (buy_flag == 1) {

//                        money = 500;
                        money = 0.01;
                        subject = "6个月";

                    } else {

//                        money = 900;
                        money = 0.01;
                        subject = "12个月";
                    }

                    try {
                        okhttpGet(StringUtils.HTTP_SERVICE + StringUtils.ZHIFUBAOZHIFU + "?money=" + money + "&subject=" + subject + "&userid=" + USERID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

    }


    //  okHttp get 方法

    //************************************************
    public    void  okhttpGet(String strurl) throws IOException {
        //创建网络处理的对象
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(strurl).build();
        //call就是我们可以执行的请求类
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
                Log.e("--->","get--->"+Thread.currentThread().getName() + "结果  " + e.toString());

            }
            @Override
            public void onResponse(Call call, Response response   ) throws IOException {



                String sequest = response.body().string();
                Log.e("--->","get--->"+Thread.currentThread().getName() + "结果  " + sequest);

                try {
                    JSONObject jsonObject =new JSONObject(sequest);
                    final String orderInfo = jsonObject.getString("order");

                    Log.e("-----> 测试信息0",orderInfo);
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            Log.e("-----> 测试信息1","");
                            PayTask alipay = new PayTask(BuyActivity.this);
//                    String result = alipay.payV2(orderInfo,true);
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = result;
                            mHandler.sendMessage(msg);

                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }




    public void toXuanZe(int flag){

        if (flag==1){
            imWX.setImageResource(R.mipmap.success);
            wx_blooean = true;
            imZFB.setImageResource(R.mipmap.circle);
            zfb_blooean = false;
        }else {
            imZFB.setImageResource(R.mipmap.success);
            zfb_blooean = true;
            imWX.setImageResource(R.mipmap.circle);
            wx_blooean = false;
        }


    }



    public void toXuanZeBuy(int flag){

        if (flag==1){
            buyOne.setImageResource(R.mipmap.btnt);
            tv_buy_one.setTextColor(Color.parseColor("#FFFFFF"));
            byTwo.setImageResource(R.mipmap.btn100);
            tv_buy_two.setTextColor(Color.parseColor("#249DFF"));

        }else {
            buyOne.setImageResource(R.mipmap.btn100);
            tv_buy_one.setTextColor(Color.parseColor("#249DFF"));
            byTwo.setImageResource(R.mipmap.btn);
            tv_buy_two.setTextColor(Color.parseColor("#FFFFFF"));
        }


    }

    WXBean wxBean;

    String packages;
    String appId;
    String sign;
    String partnerid;
    String prepayid;
    String nonceStr;
    String timeStamp;

    MoneryBean moneryBean ;
    String sixMonths;
    String twelvemonth;

    @Override
    public void onResponse(String sequest, int type) {

        Gson gson =new Gson();
        if (type==0x01) {
            wxBean = gson.fromJson(sequest, WXBean.class);
            if (wxBean.getCode()==1000){
                WXBean.DataBean dataBean = wxBean.getData();
                timeStamp =dataBean.getTimestamp();
                packages = dataBean.getPackageX();
                sign =dataBean.getSign();
                appId= dataBean.getAppid();
                prepayid =dataBean.getPrepayid();
                partnerid =dataBean.getPartnerid();
                nonceStr = dataBean.getNoncestr();

                Message msg =mHandler.obtainMessage();
                msg.what = 1;
                mHandler.sendMessage(msg);

            }
        }else if (type==0x10){


            moneryBean =gson.fromJson(sequest,MoneryBean.class);
            if (moneryBean.getCode()==1000){

                sixMonths = moneryBean.getData().getSixMonths();
                twelvemonth=moneryBean.getData().getTwelvemonth();
                Message msg =mHandler.obtainMessage();
                msg.what = 2;
                mHandler.sendMessage(msg);
            }

        }
    }

    @Override
    public void onFailure(String exp) {

    }
}
