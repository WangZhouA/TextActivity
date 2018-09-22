package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.BulletinBoardDetailsBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

public class BulletinBoardDetailsActivity extends BaseActivity implements HttpRequestCallback {


    @BindView(R.id.board_details_tv)
    TextView boardDetailsTv;
    @BindView(R.id.board_details_time)
    TextView boardDetailsTime;
    @BindView(R.id.board_details_yiyuan)
    TextView boardDetailsYiyuan;
    @BindView(R.id.board_details_pic)
    ImageView boardDetailsPic;
    @BindView(R.id.board_details_msg)
    TextView boardDetailsMsg;
    @BindView(R.id.board_details_people)
    TextView boardDetailsPeople;
    private int QUERYTITLE = 0x01;


    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 0:

                    boardDetailsTv.setText( detailsBean.getData().getNoticeTitle());
                    boardDetailsTime.setText(detailsBean.getData().getTitleOfTime());
                    boardDetailsYiyuan.setText(detailsBean.getData().getHospitalName());
                    StringUtils.showImage(BulletinBoardDetailsActivity.this,StringUtils.HTTP_SERVICE+detailsBean.getData().getImgUrl(),
                            R.mipmap.picaa,R.mipmap.picaa,boardDetailsPic);
                    boardDetailsMsg.setText(detailsBean.getData().getNoticeCountent());
                    boardDetailsPeople.setText(detailsBean.getData().getPageView()+"");


                    break;
            }

        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bulletin_board_details);
    }

    @Override
    protected void initData() {



        showNormeBar();

        Intent intent = getIntent();
        int ID = intent.getIntExtra("ID", 0);
//        HttpUtils.getInstance(BulletinBoardDetailsActivity.this).getSyncHttp(QUERYTITLE, StringUtils.HTTP_SERVICE + StringUtils.DETAILS + ID, this);

        Map<String, String> maps = new ArrayMap<>();
        maps.put("id",ID+"");
        maps.put("userId", SPUtils.getInt(BulletinBoardDetailsActivity.this,MyConstaints.USER_ID,0)+"");
        BigModle.getInstance(BulletinBoardDetailsActivity.this).getData(BulletinBoardDetailsActivity.this, maps, QUERYTITLE, this, StringUtils.DETAILS);




    }


    BulletinBoardDetailsBean detailsBean;

    @Override
    public void onResponse(String sequest, int type) {

        Gson gson = new Gson();
        detailsBean = gson.fromJson(sequest, BulletinBoardDetailsBean.class);
        handler.sendEmptyMessage(0);

    }

    @Override
    public void onFailure(String exp) {

    }



}
