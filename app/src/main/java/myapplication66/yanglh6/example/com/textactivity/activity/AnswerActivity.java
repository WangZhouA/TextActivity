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
import myapplication66.yanglh6.example.com.textactivity.adapter.AnswerAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.FenLei;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeTitleCallback;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/6/22.
 */
public class AnswerActivity extends BaseActivity implements HttpRequestCallback,NoticeTitleCallback{

    @BindView(R.id.recyclerViewAnswer)
    RecyclerView recyclerViewAnswer;

    private AnswerAdapter adapter;

    List<FenLei.DataBean>dataBeanList=new ArrayList<>();

    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {

            adapter.notifyDataSetChanged();

        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_system_is_the_answer);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.System_answer));


        HttpUtils.getInstance(AnswerActivity.this).getSyncHttp(0X01, StringUtils.HTTP_SERVICE+StringUtils.QUERY_FENLEI_TITLE+ SPUtils.getString(AnswerActivity.this,"YID",""),this);


        //设置布局管理器
        recyclerViewAnswer.setHasFixedSize(true);
        recyclerViewAnswer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnswerAdapter(this, dataBeanList);
        adapter.setCallback(this);
        recyclerViewAnswer.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerViewAnswer.setAdapter(adapter);

    }

    FenLei fenLei;
    @Override
    public void onResponse(String sequest, int type) {

        Gson gson =new Gson();
        fenLei =gson.fromJson(sequest,FenLei.class);
        dataBeanList.clear();
        dataBeanList.addAll(fenLei.getData());
        handler.sendEmptyMessage(0);

    }

    @Override
    public void onFailure(String exp) {

    }

    @Override
    public void noticeListener(int postion) {

        change(ClassificationAnswerActicity.class,this,new Intent().putExtra("ID",postion),false);
    }
}
