package myapplication66.yanglh6.example.com.textactivity.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.MsgAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MsgBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/9/4.
 */
public class MsgActivity extends BaseActivity implements HttpRequestCallback {

    @BindView(R.id.listviews)
    SwipeMenuListView listviews;

    MsgAdapter msgAdapter;

    List<MsgBean.DataBean>dataBeanList =new ArrayList<>();


    private android.os.Handler mHander =new android.os.Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){


                case 0:

                    msgAdapter.notifyDataSetChanged();

                    break;

                case 1:

                    queryList();

                    break;
            }

        }
    };
    @Override
    protected void initView() {

        setContentView(R.layout.activity_msg);
    }

    @Override
    protected void initData() {

        showNormeBar();
//        setTile(getResources().getString(R.string.dervice_item));
        setTile("消息");

        initViewA();
        msgAdapter =new MsgAdapter(this,dataBeanList);
        listviews.setAdapter(msgAdapter);
        queryList();

    }


    private void queryList(){

        HttpUtils.getInstance(MsgActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.MSG_LIST+ SPUtils.getInt(this, MyConstaints.USER_ID,0) ,this);


    }




    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private void initViewA() {

        //加入侧滑显示的菜单
        //1.首先实例化SwipeMenuCreator对象
        SwipeMenuCreator creater = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem reName = new SwipeMenuItem(MsgActivity.this);
                reName.setBackground(new ColorDrawable(Color.parseColor("#FF0000")));
                reName.setWidth(dp2px(72));
                reName.setTitleSize(17);
                reName.setTitle(getResources().getString(R.string.hide));
                reName.setTitleColor(Color.WHITE);
                menu.addMenuItem(reName);



            }
        };
        // set creator
        listviews.setMenuCreator(creater);


        //2.菜单点击事件
        listviews.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {

                    case 0:

//                        change(StartConntionWifiActivity.class,DerviceItemActivity.this,new Intent(),true);
                      try {
//                          dataBeanList.remove(position);
                          okhttpDelete(position);
                      }catch (Exception e){

                      }



                        Log.e("---->11","11");

                        break;
                    case 1:

//                        odifymNameDialog(position);

                        Log.e("---->22","22");

                        break;
                }
                return false;
            }
        });
    }

    private void okhttpDelete(int position) {

        Log.e("----->POS",position+"");
        HttpUtils.getInstance(MsgActivity.this).getSyncHttp(0x02, StringUtils.HTTP_SERVICE+StringUtils.DELETE_MSG+dataBeanList.get(position).getId(),this);

    }


    MsgBean msgBean;
    @Override
    public void onResponse(String sequest, int type) {


        Gson gson =new Gson();
        msgBean=gson.fromJson(sequest,MsgBean.class);
        if (type==0x01){
            dataBeanList.clear();
            dataBeanList.addAll(msgBean.getData());
            mHander.sendEmptyMessage(0);
        }else {

            try {
                JSONObject jsonObject =new JSONObject(sequest);
                if (jsonObject.getInt("code")==1000){
                    mHander.sendEmptyMessage(1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void onFailure(String exp) {

    }
}
