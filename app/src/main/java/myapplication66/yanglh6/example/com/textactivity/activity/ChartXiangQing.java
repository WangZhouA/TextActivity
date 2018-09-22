package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import myapplication66.yanglh6.example.com.textactivity.MainActivity;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.DayXiangQingBean;
import myapplication66.yanglh6.example.com.textactivity.entity.DoctorPrescribedBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/7/24.
 */
public class ChartXiangQing extends BaseActivity implements HttpRequestCallback {
    @BindView(R.id.header_right_msg)
    TextView headerRightMsg;
    @BindView(R.id.top_rongdu_One)
    TextView topRongduOne;
    @BindView(R.id.top_rongdu_Two)
    TextView topRongduTwo;
    @BindView(R.id.top_rongdu_Three)
    TextView topRongduThree;
    @BindView(R.id.top_rongdu_Four)
    TextView topRongduFour;
    @BindView(R.id.top_rongdu_Five)
    TextView topRongduFive;
    @BindView(R.id.top_time_One)
    TextView topTimeOne;
    @BindView(R.id.top_time_Two)
    TextView topTimeTwo;
    @BindView(R.id.top_time_Three)
    TextView topTimeThree;
    @BindView(R.id.top_time_Four)
    TextView topTimeFour;
    @BindView(R.id.top_time_Five)
    TextView topTimeFive;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.down_time_One)
    TextView downTimeOne;
    @BindView(R.id.down_time_Two)
    TextView downTimeTwo;
    @BindView(R.id.down_time_Three)
    TextView downTimeThree;
    @BindView(R.id.down_time_Four)
    TextView downTimeFour;
    @BindView(R.id.down_time_Five)
    TextView downTimeFive;
    @BindView(R.id.down_rongdu_One)
    TextView downRongduOne;
    @BindView(R.id.down_rongdu_Two)
    TextView downRongduTwo;
    @BindView(R.id.down_rongdu_Three)
    TextView downRongduThree;
    @BindView(R.id.down_rongdu_Four)
    TextView downRongduFour;
    @BindView(R.id.down_rongdu_Five)
    TextView downRongduFive;

    TextView[] tvStr;
    TextView[] tvStrHour;
    TextView[] tvStrConcentration;

    List<String> liststr = new ArrayList<>();
    List<String> listHour = new ArrayList<>();
    List<String> listConcentration = new ArrayList<>();


    List<String> dowenListFrequency = new ArrayList<>();
    List<String> dowenListTime = new ArrayList<>();
    List<String> dowenListConcentration = new ArrayList<>();
    List<String> dowenListUltrafiltration = new ArrayList<>();

    TextView tvStrDown[];
    TextView[] tvStrHourDown;
    TextView[] tvStrConcentrationDown;
    TextView[] tvStrultrafiltrationDown;


//    @BindView(R.id.top_one)
//    TextView topOne;
//    @BindView(R.id.top_Two)
//    TextView topTwo;
//    @BindView(R.id.top_three)
//    TextView topThree;
//    @BindView(R.id.top_four)
//    TextView topFour;
//    @BindView(R.id.top_five)
//    TextView topFive;
//    @BindView(R.id.down_one)
//    TextView downOne;
//    @BindView(R.id.down_two)
//    TextView downTwo;
//    @BindView(R.id.down_three)
//    TextView downThree;
//    @BindView(R.id.down_four)
//    TextView downFour;
//    @BindView(R.id.down_five)
//    TextView downFive;

    @BindView(R.id.header_left)
    ImageButton headerLeft;
    @BindView(R.id.down_one_fuye)
    TextView downOneFuye;
    @BindView(R.id.down_two_fuye)
    TextView downTwoFuye;
    @BindView(R.id.down_three_fuye)
    TextView downThreeFuye;
    @BindView(R.id.down_four_fuye)
    TextView downFourFuye;
    @BindView(R.id.down_five_fuye)
    TextView downFiveFuye;


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 0:
                    setDate(liststr, listHour, listConcentration);
                    break;
                case 1:
                    setDateDown(dowenListFrequency, dowenListTime, dowenListConcentration,dowenListUltrafiltration);
                    tvShijian.setText(dayXiangQingBean.getData().get(0).getDate()+"("+dayXiangQingBean.getData().get(0).getWeeks()+")");
                    break;

            }

        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_chartxiangqing);
    }


    List<String> listDate;
    String TimeDate;

    @Override
    protected void initData() {

        setTile(getResources().getString(R.string.xiangq));
        headerRightMsg.setVisibility(View.VISIBLE);
        headerRightMsg.setText(getResources().getString(R.string.colse));
        headerLeft.setVisibility(View.GONE);
        Intent intent = getIntent();

        int index = intent.getIntExtra("x", 0);

//        listDate = MainActivity.getLi9st();
        listDate = MainActivity.getLi9stCOPY();

        Log.e("___index",index+"");
//
//        if (index==0){
//
//
//        }else {
//
//
//            /**
//             *  这里思路错误了，应该是去 拿数据的时候再存储一个完整的集合， 2个集合的的下标是一至的。取到的那个下标就直接去拿那个列表的下标
//             * */
//
//
//
//        }


        if (listDate != null) {

            Log.e("____数据",listDate.get(index));
            String [] SP = listDate.get(index).split("\n");
//            if (SP[0].length()==5) {
//                Log.e("____数据A",SP[0]);
//                TimeDate = Util.getYear() + "/" + listDate.get(index);
//                TimeDate = TimeDate.substring(0, 10);
//            }else {
//                Log.e("____数据B",SP[0]);
//                TimeDate = Util.getYear() + "/"+Util.getMont()+"/"+ listDate.get(index);
//                TimeDate = TimeDate.substring(0, 10);
//            }


            TimeDate =SP[0].substring(0,10).replaceAll("-", "/");
            Map<String, String> maps = new ArrayMap<>();
            maps.put("userId", SPUtils.getInt(this, MyConstaints.USER_ID, 0) + "");
            HttpUtils.getInstance(this).postAsynHttp(StringUtils.DOCTOR_PRESCRIBED, maps, 0x01, this);
        }


//        tvStr = new TextView[]{topOne, topTwo, topThree, topFour, topFive};
        tvStrHour = new TextView[]{topTimeOne, topTimeTwo, topTimeThree, topTimeFour, topTimeFive};
        tvStrConcentration = new TextView[]{topRongduOne, topRongduTwo, topRongduThree, topRongduFour, topRongduFive};

//        tvStrDown = new TextView[]{downOne, downTwo, downThree, downFour, downFive};
        tvStrHourDown = new TextView[]{downTimeOne, downTimeTwo, downTimeThree, downTimeFour, downTimeFive};
        tvStrConcentrationDown = new TextView[]{downRongduOne, downRongduTwo, downRongduThree, downRongduFour, downRongduFive};
        tvStrultrafiltrationDown = new TextView[]{downOneFuye,downTwoFuye,downThreeFuye,downFourFuye,downFiveFuye,};



        Map<String, String> daymaps = new ArrayMap<>();
        daymaps.put("time", TimeDate);
        daymaps.put("userId", SPUtils.getInt(ChartXiangQing.this, MyConstaints.USER_ID,0)+"");
        BigModle.getInstance(ChartXiangQing.this).getData(ChartXiangQing.this, daymaps, 0x02, this, StringUtils.FIND_OUT_THE_DATE_DETAILS);


    }


    @OnClick(R.id.header_right_msg)
    public void onViewClicked() {
        finish();
    }


    List<DoctorPrescribedBean.DataBean.PrescriptionsBean> DoctorBean;
    DoctorPrescribedBean doctorPrescribedBean;

    DayXiangQingBean dayXiangQingBean;
    List<DayXiangQingBean.DataBean.EqudataDetailsBean> dataBeanList;

    @Override
    public void onResponse(String sequest, int type) {

        Gson gson = new Gson();
        if (type == 0x01) {
            doctorPrescribedBean = gson.fromJson(sequest, DoctorPrescribedBean.class);
            if (doctorPrescribedBean.getCode()==1000) {
                DoctorBean = doctorPrescribedBean.getData().get(0).getPrescriptions();
                liststr.clear();
                listHour.clear();
                listConcentration.clear();
                for (int i = 0; i < DoctorBean.size(); i++) {

                    liststr.add(DoctorBean.get(i).getFrequency());
                    listHour.add(DoctorBean.get(i).getHour());
                    listConcentration.add(DoctorBean.get(i).getConcentration());

                }

                handler.sendEmptyMessage(0);
            }

        } else if (type == 0x02) {

            dayXiangQingBean = gson.fromJson(sequest, DayXiangQingBean.class);

            if (dayXiangQingBean.getCode() == 1000) {
                dataBeanList = dayXiangQingBean.getData().get(0).getEqudataDetails();

                dowenListFrequency.clear();
                dowenListTime.clear();
                dowenListConcentration.clear();
                dowenListUltrafiltration.clear();

                for (int i = 0; i < dataBeanList.size(); i++) {

                    dowenListFrequency.add(dataBeanList.get(i).getFrequency());
                    dowenListTime.add(dataBeanList.get(i).getTime());
                    dowenListConcentration.add(dataBeanList.get(i).getConcentration());
                    dowenListUltrafiltration.add(dataBeanList.get(i).getUltrafiltration());

                }

                handler.sendEmptyMessage(1);

            }
        }
    }

    @Override
    public void onFailure(String exp) {

        Log.e("--->exp",exp.toString());

    }


    private void setDate(List<String> lists, List<String> listhor, List<String> listConcentration) {

//        for (int i = 0; i < lists.size(); i++) {
//
//            tvStr[i].setText("第" + lists.get(i) + "次");
//        }
        for (int i = 0; i < listhor.size(); i++) {

            tvStrHour[i].setText(listhor.get(i));
        }
        for (int i = 0; i < listConcentration.size(); i++) {

            tvStrConcentration[i].setText(listConcentration.get(i));
        }
    }

    private void setDateDown(List<String> lists, List<String> listhor, List<String> listConcentration,List<String> listUltrafiltration) {

//        for (int i = 0; i < lists.size(); i++) {
//
//            tvStrDown[i].setText("第" + lists.get(i) + "次");
//        }
        for (int i = 0; i < listhor.size(); i++) {

            tvStrHourDown[i].setText(listhor.get(i));
        }
        for (int i = 0; i < listConcentration.size(); i++) {

            tvStrConcentrationDown[i].setText(listConcentration.get(i));
        }
        for (int i = 0; i < listUltrafiltration.size(); i++) {

            tvStrultrafiltrationDown[i].setText(listUltrafiltration.get(i));
        }
    }

}


