package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.BulletinAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.BullinXiangQingBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeMsgCallback;
import myapplication66.yanglh6.example.com.textactivity.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/7/10.
 */
public class BulletinActivity extends BaseActivity implements HttpRequestCallback, NoticeMsgCallback {
    @BindView(R.id.bulletin_recyvlerView)
    RecyclerView recyclerViewTitle;
    BulletinAdapter adapter;

    List<BullinXiangQingBean.DataBean> dataBeanList = new ArrayList<>();


    private int QUERYTITLE = 0x01;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case 0:
                    Log.e("--->", "11");
                    if (dataBeanList.size() > 0) {
                        adapter.notifyDataSetChanged();
                    } else {

                        toast(R.string.no_date);
                    }


                    break;
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bulletin);
    }

    int ID;
    @Override
    protected void initData() {


        Intent intent = getIntent();
         ID = intent.getIntExtra("ID", 0);
//        String title = intent.getStringExtra("TITLE");
        setTile("详情");
        showNormeBar();


        //设置布局管理器
        recyclerViewTitle.setHasFixedSize(true);
        recyclerViewTitle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BulletinAdapter(this, dataBeanList);
        adapter.setCallback(this);
        recyclerViewTitle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerViewTitle.setAdapter(adapter);

    }

    BullinXiangQingBean dateList;

    @Override
    public void onResponse(String sequest, int type) {
        Gson gson = new Gson();

        if (type == QUERYTITLE) {
            dateList = gson.fromJson(sequest, BullinXiangQingBean.class);
            dataBeanList.clear();
            dataBeanList.addAll(dateList.getData());
            handler.sendEmptyMessage(0);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        HttpUtils.getInstance(BulletinActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.QUERY_GONGGAOLAN_FENLEI_XIANGQING + ID, this);
    }

    @Override
    public void onFailure(String exp) {

    }

    @Override
    public void setIdListener(int id) {
        change(BulletinBoardDetailsActivity.class, BulletinActivity.this, new Intent().putExtra("ID", id), false);
    }

}
