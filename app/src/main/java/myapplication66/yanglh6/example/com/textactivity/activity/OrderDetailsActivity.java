package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.OrderDetailsAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.OrderDetailsBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.OnorderListener;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.LogUtils;

/**
 * Created by 陈姣姣 on 2018/6/29.
 */
public class OrderDetailsActivity extends BaseActivity implements OnorderListener,HttpRequestCallback{


    @BindView(R.id.recyclerVieDetail)
    RecyclerView recyclerViewOrderDetails;

    @BindView(R.id.header_all)
    LinearLayout rlAll;

    @BindView(R.id.header_text)
    TextView  textText;

    List<OrderDetailsBean.DataBean>dataBeanList=new ArrayList<>();

    OrderDetailsAdapter adapter;


    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case 0:

                    adapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activiy_detail);
    }

    @Override
    protected void initData() {

        showNormeBar();
        backImageView(R.mipmap.back_b);
        textText.setTextColor(Color.parseColor("#242424"));
        textText.setText(getResources().getString(R.string.The_detail));
        //设置沉浮状态栏 flag 是否沉浮__适用于滑动的viewpager  true为沉浮
        setWindowfullScreen(true);
        //系统状态栏颜色
        setWindowStatusBarColor(Color.parseColor("#FFFFFF"));
        rlAll.setBackgroundColor(Color.parseColor("#FFFFFF"));

        HttpUtils.getInstance(OrderDetailsActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.ORDER_DETAILS+ SPUtils.getInt(this, MyConstaints.USER_ID,0) ,this);

        recyclerViewOrderDetails.setHasFixedSize(true);
        recyclerViewOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderDetailsAdapter(this, dataBeanList);
        adapter.setNoticeTitleCallback(this);
        recyclerViewOrderDetails.setAdapter(adapter);
    }



    OrderDetailsBean orderDetailsBean;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson();
        orderDetailsBean=gson.fromJson(sequest,OrderDetailsBean.class);
        dataBeanList.clear();
        dataBeanList.addAll(orderDetailsBean.getData());
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onFailure(String exp) {



    }

    @Override
    public void orderListener(String s) {
        LogUtils.e("="+s);
        change(BillingDetailsActivity.class,this,new Intent().putExtra("num",s),false);
    }
}
