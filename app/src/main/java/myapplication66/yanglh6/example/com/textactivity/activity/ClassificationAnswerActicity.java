package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.TestSummaryAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.TestSummaryBean;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.IOnScrollListener;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NotifiyCallBack;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.MyDialog;
import myapplication66.yanglh6.example.com.textactivity.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/6/26.
 */
public class ClassificationAnswerActicity extends BaseActivity implements HttpRequestCallback, NotifiyCallBack {


    @BindView(R.id.submit_btn)
    Button submitBtn;
    private int QUERYTITLE = 0x01;
    List<TestSummaryBean.DataBean> dataBeanList = new ArrayList<>();

    @BindView(R.id.recyclerViewAnswer)
    RecyclerView recyAnswer;

    TestSummaryAdapter testSummaryAdapter;


    IOnScrollListener scrollListener;

    @BindView(R.id.btn_lin)
    LinearLayout linearLayout_btn;

    int ID;

    public void setScrollListener(IOnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            testSummaryAdapter.setNotify(dataBeanList);

        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_classificationanswer);
    }

    @Override
    protected void initData() {


        setTile(getResources().getString(R.string.System_answer));
        showNormeBar();

        Intent intent = getIntent();
        ID = intent.getIntExtra("ID", -1);
        Log.e("--->ID",ID+"");
        HttpUtils.getInstance(ClassificationAnswerActicity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.TEST_SUMMARY + ID, this);
        //设置布局管理器
        recyAnswer.setHasFixedSize(true);
        recyAnswer.setLayoutManager(new LinearLayoutManager(this));
        testSummaryAdapter = new TestSummaryAdapter(this, dataBeanList);
        testSummaryAdapter.setNotifiyCallBack(this);
        recyAnswer.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recyAnswer.setAdapter(testSummaryAdapter);
        recyAnswer.setOnScrollListener(onScrollListener);


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

    int lastItemPosition;
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //onScrollStateChanged 方法
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            //判断是当前layoutManager是否为LinearLayoutManager
            //只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                //获取最后一个可见view的位置
                lastItemPosition = linearManager.findLastVisibleItemPosition();
                //获取第一个可见view的位置
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();

            }

            //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
            //如果相等则说明已经滑动到最后了
            if (lastItemPosition == recyclerView.getLayoutManager().getItemCount() - 1) {

                Log.e("----->滑动到底了", "滑动到底了");
                linearLayout_btn.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy < 0) {
                Intent i = new Intent("UPTE");
                Log.e("----->上", "上");
                linearLayout_btn.setVisibility(View.GONE);

            } else {
                Intent i = new Intent("DOWN");
                Log.e("----->下", "下");
            }
        }
    };

    @Override
    public void notifiy() {

        submitBtn.setBackgroundResource(R.drawable.btn_bgs_false);
        submitBtn.setEnabled(!submitBtn.isEnabled());

    }


    List<Integer>Erroslists =new ArrayList<>();
    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        if(testSummaryAdapter != null && !testSummaryAdapter.hasNoAnswer()){
            //提交
            String []  answer=testSummaryAdapter.getAnswers();
            if (answer!=null){
                Log.e("--->anwer", Arrays.toString(answer));

                for (int i =0;i< answer.length;i++){

                    if (!answer[i].contains(testSummaryBean.getData().get(i).getRealAnswer())){

                        Erroslists.add(i);
                    }

                }

                Log.e("---->错误答题为",Erroslists.toString());
                int  defen  =100;
                defen =defen-(Erroslists.size()*5);
                int  zhengqueDeFen = 20 - ( Erroslists.size());
                ShowDiaLog(zhengqueDeFen, Erroslists.size(),defen);
                Erroslists.clear();
                Map<String, String> maps =new ArrayMap<>();
                maps.put("achievement",defen+"");
                maps.put("questionGroup",ID+"");
                maps.put("userId", SPUtils.getInt(ClassificationAnswerActicity.this, MyConstaints.USER_ID,0)+"");
                BigModle.getInstance(ClassificationAnswerActicity.this).getData(ClassificationAnswerActicity.this,maps,0x02, this,StringUtils.ANSWERSLIST);

            }

        }else{
            toast("您还有题目未回答");
        }
    }


    private  void  ShowDiaLog(int   TrueMin , int TvErros , int  CountFen){

        LayoutInflater inflater =LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_anwser_list,null);
        TextView tvTrue = view.findViewById(R.id.tv_true_minute);
        TextView tvErros = view.findViewById(R.id.tv_erros_minute);
        TextView tvCountFen = view.findViewById(R.id.tv_countFen_minute);
        Button btn =view.findViewById(R.id.btn_yes);
        ImageView im_x =view.findViewById(R.id.im_X);
        final   MyDialog myDialog =new MyDialog(this,0,0,view,R.style.MyDialog);
        tvTrue.setText(TrueMin+"");
        tvErros.setText(TvErros+"");
        tvCountFen.setText(CountFen+"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change(TrueAnswersActivity.class,ClassificationAnswerActicity.this,new Intent().putExtra("ID",ID),true);
                myDialog.dismiss();
            }
        });
        im_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.show();

    }

}
