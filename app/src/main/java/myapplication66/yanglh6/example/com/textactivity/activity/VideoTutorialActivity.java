package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.VideoAdapterQuery;
import myapplication66.yanglh6.example.com.textactivity.adapter.VideoTutorialAdapter;
import myapplication66.yanglh6.example.com.textactivity.adapter.VideoTutorialAdapterCopy;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.VideoBean;
import myapplication66.yanglh6.example.com.textactivity.entity.VideoQueryBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/7/18.
 */
public class VideoTutorialActivity extends BaseActivity implements HttpRequestCallback,View.OnClickListener ,TextView.OnEditorActionListener {


    @BindView(R.id.header_all)
    LinearLayout headerAll;

    @BindView(R.id.rl_bg)
    ImageView rl_bg;

    @BindView(R.id.header_right)
    ImageButton headerRight;
    @BindView(R.id.first_title)
    TextView firstTitle;
    @BindView(R.id.one_title)
    TextView oneTitle;
    @BindView(R.id.jingxuan_rl)
    RecyclerView jingxuanRl;
    @BindView(R.id.putong_rl)
    RecyclerView putongRl;

    private VideoTutorialAdapter adapter;
    private VideoTutorialAdapterCopy adapterCopy;

    List<VideoBean.DataBean.VideoInfosBean>dataBeanListA=new ArrayList<>();
    List<VideoBean.DataBean.VideoInfosBean>dataBeanListB=new ArrayList<>();
    VideoBean videoBean;

    @BindView(R.id.re_sousuokuang)
    RecyclerView itemRl;
    @BindView(R.id.Lin_sousuo )
    LinearLayout LinSousuo;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.cancel)
    TextView Cancel;

    VideoAdapterQuery videoAdapterQuery;

    int  dingBuId=0;
    @BindView(R.id.rl_dingbu_videor)


    RelativeLayout rl_dingbuVideor;


    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 0:


                    firstTitle.setText(videoBean.getData().get(0).getVideoInfos().get(0).getTitle());
                    StringUtils.showImage(VideoTutorialActivity.this,StringUtils.HTTP_SERVICE+videoBean.getData().get(0).getVideoInfos().get(0).getVideoUrl(),R.mipmap.picaa,R.mipmap.picaa,rl_bg);

                    adapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();


                    break;

                case 1:
                    itemRl.setVisibility(View.VISIBLE);
                    videoAdapterQuery.notifyDataSetChanged();

                    break;
            }
        }
    };

    @Override
    protected void initView() {

        setContentView(R.layout.activity_video_tutorial);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.video_course));

        headerRight.setVisibility(View.VISIBLE);
        headerRight.setImageResource(R.mipmap.so);
        headerRight.setOnClickListener(this);
        jingxuanRl.setHasFixedSize(true);
        jingxuanRl.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new VideoTutorialAdapter(this, dataBeanListA);
        jingxuanRl.setAdapter(adapter);
        Cancel.setOnClickListener(this);


        putongRl.setHasFixedSize(true);
        putongRl.setLayoutManager(new GridLayoutManager(this,2));
        adapterCopy = new VideoTutorialAdapterCopy(this, dataBeanListB);
        putongRl.setAdapter(adapterCopy);
        etSousuo.setOnEditorActionListener(this);


        itemRl.setHasFixedSize(true);
        itemRl.setLayoutManager(new LinearLayoutManager(this));
        videoAdapterQuery = new VideoAdapterQuery(this, listQuery);
        itemRl.setAdapter(videoAdapterQuery);

        rl_dingbuVideor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(VideoTutorialActivity.this, BoFangActivity.class).putExtra("videoUrl",dingBuURL));
            }
        });




    }


    String dingBuURL="";
    @Override
    public void onResponse(String sequest, int type) {

        Gson gson = new Gson();
        videoBean=gson.fromJson(sequest,VideoBean.class);

        if (type==0x01){

            dataBeanListA.clear();
            dataBeanListB.clear();


            for (int i=0;i<videoBean.getData().size();i++){

//                if (videoBean.getData().get(i).getName().contains("顶部视频")){
//                    dingBuId =videoBean.getData().get(i).getVideoInfos().get(0).getId();
//                    dingBuURL = videoBean.getData().get(i).getVideoInfos().get(0).getVideoUrl();

//                }else if (videoBean.getData().get(i).getName().contains("精选视频")){
//
//                    dataBeanListA.addAll(videoBean.getData().get(i).getVideoInfos());
//                }else if (videoBean.getData().get(i).getName().contains("普通视频")){
//                    dataBeanListB.addAll(videoBean.getData().get(i).getVideoInfos());
                    dataBeanListA.addAll(videoBean.getData().get(i).getVideoInfos());
                }

            }

            handler.sendEmptyMessage(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        headerAll.setVisibility(View.VISIBLE);
        LinSousuo.setVisibility(View.GONE);
        itemRl.setVisibility(View.GONE);
//        SPUtils.getInt(VideoTutorialActivity.this,"CODEID",0);
        HttpUtils.getInstance(this).getSyncHttp(0x01,StringUtils.HTTP_SERVICE+StringUtils.VIDEOITEM+"&hospitalId="+ SPUtils.getString(VideoTutorialActivity.this,"YID",""),this);
    }

    @Override
    public void onFailure(String exp) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.header_right:

                http://www.999d.com/video/98193.html?source=baidu_open

                headerAll.setVisibility(View.GONE);
                LinSousuo.setVisibility(View.VISIBLE);

                break;

            case  R.id.cancel:

                headerAll.setVisibility(View.VISIBLE);
                LinSousuo.setVisibility(View.GONE);
                itemRl.setVisibility(View.GONE);
                break;


        }
    }


    VideoQueryBean videoQueryBean;
    List<VideoQueryBean.DataBean.VideoInfosBean>listQuery=new ArrayList<>();
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch(actionId){
            case EditorInfo.IME_NULL:
                System.out.println("Done_content: " + v.getText() );
                break;
            case EditorInfo.IME_ACTION_SEND:
                System.out.println("send a email: "  + v.getText());
                break;
            case EditorInfo.IME_ACTION_DONE:
                System.out.println("action done for number_content: "  + v.getText());

                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    HttpUtils.getInstance(VideoTutorialActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE + StringUtils.VIDEOITEM+v.getText()+"&hospitalId="+ SPUtils.getString(VideoTutorialActivity.this,"YID",""), new HttpRequestCallback() {
                        @Override
                        public void onResponse(String sequest, int type) {

                            Gson gson=new Gson();
                            listQuery.clear();
                            videoQueryBean= gson.fromJson(sequest,VideoQueryBean.class);
                            for (int i=0;i<videoQueryBean.getData().size();i++) {
                                listQuery.addAll(videoQueryBean.getData().get(i).getVideoInfos());
                            }
                            handler.sendEmptyMessage(1);
                        }

                        @Override
                        public void onFailure(String exp) {

                        }
                    });



                }

                break;
        }

        return true;
    }

}
