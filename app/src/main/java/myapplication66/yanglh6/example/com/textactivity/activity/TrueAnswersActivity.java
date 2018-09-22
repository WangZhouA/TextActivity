package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.TrueAnswersAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.TestSummaryBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/7/18.
 */
public class TrueAnswersActivity extends BaseActivity implements HttpRequestCallback{
    @BindView(R.id.recyclerViewAnswerA)
    RecyclerView recyclerViewAnswerA;


    TrueAnswersAdapter adapter;
    int  ID;
    List<TestSummaryBean.DataBean> dataBeanList = new ArrayList<>();


    private int  QUERYTITLE=0x01;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_trueanswers);

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            adapter.setNotify(dataBeanList);

        }
    };


    @Override
    protected void initData() {


        showNormeBar();
        setTile(getResources().getString(R.string.trueAnswers));

        Intent intent = getIntent();
        ID = intent.getIntExtra("ID", -1);

//        设置布局管理器
        recyclerViewAnswerA.setHasFixedSize(true);
        recyclerViewAnswerA.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrueAnswersAdapter(this, dataBeanList);
        recyclerViewAnswerA.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerViewAnswerA.setAdapter(adapter);


        HttpUtils.getInstance(TrueAnswersActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.TEST_SUMMARY + ID, this);

    }


    TestSummaryBean testSummaryBean;

    @Override
    public void onResponse(String sequest, int type) {

        Gson gson = new Gson();
        if (type == QUERYTITLE) {

            testSummaryBean = gson.fromJson(sequest, TestSummaryBean.class);
            dataBeanList.clear();
            dataBeanList.addAll(testSummaryBean.getData());
            handler.sendEmptyMessage(0);
        }
    }
    @Override
    public void onFailure(String exp) {

    }
}
