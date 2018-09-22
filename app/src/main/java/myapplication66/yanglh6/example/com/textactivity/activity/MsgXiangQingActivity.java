package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MsgXiangQingBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;

/**
 * Created by 陈姣姣 on 2018/9/5.
 */
public class MsgXiangQingActivity extends BaseActivity implements HttpRequestCallback{

    @BindView(R.id.TV_MSG)
    TextView tv_Msg;
    @BindView(R.id.TV_TIME)
    TextView tv_Time;

    private Handler mHandler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 0:

                    tv_Msg.setText("温馨提示：请于"+msgXiangQingBean.getData().getRemindingTime()+"到"+msgXiangQingBean.getData().getHospitalName()+msgXiangQingBean.getData().getMessageContent());
                    tv_Time.setText(msgXiangQingBean.getData().getCreationTime());

                    break;
            }

        }
    };

    @Override   protected void initView() {

        setContentView(R.layout.actvity_msg_xiangqing);

    }

    @Override
    protected void initData() {


        showNormeBar();
        setTile("详情");

        Intent intent =getIntent();

        if (intent.getIntExtra("IDS",-1)!=-1){

            HttpUtils.getInstance(MsgXiangQingActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.MSG_XIANGQING+ intent.getIntExtra("IDS",-1),this);

        }



    }
    MsgXiangQingBean msgXiangQingBean;
    @Override
    public void onResponse(String sequest, int type) {

        Gson gson =new Gson();
        msgXiangQingBean = gson.fromJson(sequest,MsgXiangQingBean.class);

        if (msgXiangQingBean.getCode()==1000){


    mHandler.sendEmptyMessage(0);


        }

    }

    @Override
    public void onFailure(String exp) {

    }
}
