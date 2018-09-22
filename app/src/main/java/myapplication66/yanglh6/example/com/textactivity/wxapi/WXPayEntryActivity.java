package myapplication66.yanglh6.example.com.textactivity.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import myapplication66.yanglh6.example.com.textactivity.activity.BuyResultActvivity;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    IWXAPI api;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        api = WXAPIFactory.createWXAPI(this, StringUtils.APPID);
//        api.handleIntent(getIntent(),this);

        Log.e("--->zhifu","");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this,"wxe1b1532d80eb2353");
//        api.registerApp("wxe1b1532d80eb2353");
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);Log.e("---->","走newIntent");
    }



    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
           Log.e("----->MSG",baseResp.errCode+"");

            // 判断resultStatus 为9000则代表支付成功
            if (baseResp.errCode==0) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                change(BuyResultActvivity.class, WXPayEntryActivity.this, new Intent().putExtra("END",0), true);

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                change(BuyResultActvivity.class, WXPayEntryActivity.this, new Intent().putExtra("END",1), true);
            }


        }
    }
}
