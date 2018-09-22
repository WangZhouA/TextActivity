package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.NoticeMsgAdapter;
import myapplication66.yanglh6.example.com.textactivity.adapter.NoticeTitleAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.NoticeTitleBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.IonGetNameLinstener;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeTitleCallback;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/6/19.
 */
public class NoticeActivity extends BaseActivity implements HttpRequestCallback ,NoticeTitleCallback,IonGetNameLinstener{


    //查询标题

    private int QUERYTITLE = 0x01;
    private int QUERYMSG = 0x02;
    private int SHUAXIN = 0x02;

    @BindView(R.id.recyclerViewTitle)
    RecyclerView recyclerViewTitle;

    private List<NoticeTitleBean.DataBean>dataBeanList=new ArrayList<>();
    private List<NoticeTitleBean.DataBean>dataBeanListMsg=new ArrayList<>();

    NoticeTitleAdapter titleAdapter;
    NoticeMsgAdapter titleAdapterMsg;

    @BindView(R.id.recyclerViewMsg)
    RecyclerView recyclerViewMsg;




    NoticeTitleBean noticeTitleBeanMsg;

    String TName;
    int  flag= 0;
    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0:
                    if (flag==0) {
                        HttpUtils.getInstance(NoticeActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.NOTICE_MSG +  SPUtils.getInt(NoticeActivity.this,"CODEID",0), new HttpRequestCallback() {
                            @Override
                            public void onResponse(String sequest, int type) {
                                Gson gson = new Gson();
                                noticeTitleBeanMsg = gson.fromJson(sequest, NoticeTitleBean.class);
                                if (type == QUERYTITLE) {
                                    if (noticeTitleBeanMsg.isSuccess() == true) {
                                        dataBeanListMsg.clear();
                                        dataBeanListMsg.addAll(noticeTitleBeanMsg.getData());
                                        handler.sendEmptyMessage(1);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(String exp) {

                            }
                        });
                    }
                    titleAdapter.notifyDataSetChanged();

                    break;

                case 1:

                    titleAdapterMsg.notifyDataSetChanged();
                    break;

                case 2:

                    titleAdapter.notifyDataSetChanged();
                    break;

            }

        }
    };






    @Override
    protected void initView() {
        setContentView(R.layout.activity_notice);
    }

    @Override
    protected void initData() {
        setTile(getResources().getString(R.string.Bulletin_Board));
        showNormeBar();
        //设置布局管理器
        recyclerViewTitle.setHasFixedSize(true);
        recyclerViewTitle.setLayoutManager(new LinearLayoutManager(this));
        titleAdapter = new NoticeTitleAdapter(this, dataBeanList);
        titleAdapter.setCallback(this);
        titleAdapter.setNotifiyCallBack(this);
        recyclerViewTitle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerViewTitle.setAdapter(titleAdapter);


        recyclerViewMsg.setHasFixedSize(true);
        recyclerViewMsg.setLayoutManager(new LinearLayoutManager(this));
        titleAdapterMsg = new NoticeMsgAdapter(this, dataBeanListMsg);
        recyclerViewMsg.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerViewMsg.setAdapter(titleAdapterMsg);

        Intent TNameIntent= getIntent();
        if (!TextUtils.isEmpty(SPUtils.getString(NoticeActivity.this,"TName",""))) {

            TName =SPUtils.getString(NoticeActivity.this,"TName","");
        }else {
            TName = TNameIntent.getStringExtra("TName");
        }

    }
    Intent intent_clear;
    @Override
    protected void onResume() {
        super.onResume();
        flag=0;
        if (!TextUtils.isEmpty(TName)) {
            if (!TextUtils.isEmpty(SPUtils.getString(NoticeActivity.this,"TName",""))) {

                TName =SPUtils.getString(NoticeActivity.this,"TName","");
            }else {

            }

            HttpUtils.getInstance(NoticeActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.NOTICE_TITLE + SPUtils.getString(NoticeActivity.this,"YID","") + "&name=" + TName, this);
        }
        intent_clear =new Intent("clear");
        sendBroadcast(intent_clear);


    }


    NoticeTitleBean noticeTitleBean;
    @Override
    public void onResponse(String sequest, int type) {

        Gson gson =new Gson();

        if (type==QUERYTITLE){
            noticeTitleBean = gson.fromJson(sequest,NoticeTitleBean.class);
            if (noticeTitleBean.isSuccess()==true){
                dataBeanList.clear();
                dataBeanList.addAll(noticeTitleBean.getData());
                handler.sendEmptyMessage(0);
            }
        }else if (type==QUERYMSG){

            noticeTitleBeanMsg = gson.fromJson(sequest,NoticeTitleBean.class);
            dataBeanListMsg.clear();
            dataBeanListMsg.addAll(noticeTitleBeanMsg.getData());
            handler.sendEmptyMessage(1);

        }

    }

    @Override
    public void onFailure(String exp) {

    }

    @Override
    public void noticeListener(int postion) {
        Log.e("---->postion",postion+"");
        HttpUtils.getInstance(NoticeActivity.this).getSyncHttp(QUERYMSG, StringUtils.HTTP_SERVICE + StringUtils.NOTICE_MSG+postion, this);
    }

    @Override
    public void onGetName(String name) {
        flag=1;
        HttpUtils.getInstance(NoticeActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE+StringUtils.NOTICE_TITLE_ALL+  SPUtils.getString(NoticeActivity.this,"YID","")+"&name="+name ,this);
    }
}
