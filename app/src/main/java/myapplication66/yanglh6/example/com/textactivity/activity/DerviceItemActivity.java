package myapplication66.yanglh6.example.com.textactivity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.adapter.DerviceItemAdapter;
import myapplication66.yanglh6.example.com.textactivity.base.BaseActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.DerviceBean;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.view.ForPlayDialog;

/**
 * Created by 陈姣姣 on 2018/7/19.
 */
public class DerviceItemActivity extends BaseActivity implements HttpRequestCallback{


    @BindView(R.id.listSwipe)
    SwipeMenuListView listSwipe;

    DerviceItemAdapter adapter;

    List<DerviceBean.DataBean>dataBeanList =new ArrayList<>();


    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.setNotily(dataBeanList);
        }
    };


    ForPlayDialog forPlayDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_derviceitem);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.dervice_item));

        initViewA();
        adapter =new DerviceItemAdapter(this,dataBeanList);
        listSwipe.setAdapter(adapter);
        HttpUtils.getInstance(DerviceItemActivity.this).getSyncHttp(0x01, StringUtils.HTTP_SERVICE+StringUtils.DERVICEITEM+ SPUtils.getInt(this, MyConstaints.USER_ID,0) ,this);


    }

    private void initViewA() {

        //加入侧滑显示的菜单
        //1.首先实例化SwipeMenuCreator对象
        SwipeMenuCreator creater = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem reName = new SwipeMenuItem(DerviceItemActivity.this);
                reName.setBackground(new ColorDrawable(Color.parseColor("#25BB4A")));
                reName.setWidth(dp2px(72));
                reName.setTitleSize(17);
                reName.setTitle(getResources().getString(R.string.network));
                reName.setTitleColor(Color.WHITE);
                menu.addMenuItem(reName);


                SwipeMenuItem deleteItem = new SwipeMenuItem(DerviceItemActivity.this);
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FFC213" )));
                deleteItem.setWidth(dp2px(72));
                deleteItem.setTitleSize(17);
                deleteItem.setTitle(getResources().getString(R.string.rename));
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);

            }
        };
        // set creator
        listSwipe.setMenuCreator(creater);


        //2.菜单点击事件
        listSwipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {

                    case 0:


                        change(StartConntionWifiActivity.class,DerviceItemActivity.this,new Intent(),true);

                        break;
                    case 1:

                        odifymNameDialog(position);

                        break;
                }
                return false;
            }
        });

    }


    String  ed_dervice_name;
    //修改设备的请求
    private void odifymNameDialog(final int position) {

        forPlayDialog = new ForPlayDialog(DerviceItemActivity.this);
        forPlayDialog.setTitle(getResources().getString(R.string.redervice_name));
        forPlayDialog.setYesOnclickListener(getResources().getString(R.string.determine), new ForPlayDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                ed_dervice_name = forPlayDialog.setMessage();

                if (ed_dervice_name != null) {
                    //已经拿到用户舒服的名字，现在需要联网请求去改变名字了
                    //===============================
                    dataBeanList.get(position).setRqname(ed_dervice_name);
                    //==================================
                    adapter.notifyDataSetChanged();
//                    okHttpReName(position);

                        Map<String,String> map = new ArrayMap<>();
                        map.put("id",dataBeanList.get(position).getId()+"");
                        map.put("rqname",ed_dervice_name);
                        BigModle.getInstance(DerviceItemActivity.this).getData(DerviceItemActivity.this, map, 0X02, new HttpRequestCallback() {
                            @Override
                            public void onResponse(String sequest, int type) {

                                Log.e("---->更名成功",sequest.toString());

                            }

                            @Override
                            public void onFailure(String exp) {

                            }
                        },StringUtils.RENAME);


                }

                forPlayDialog.dismiss();
            }
        });
        forPlayDialog.setNoOnclickListener(getResources().getString(R.string.cancel), new ForPlayDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                forPlayDialog.dismiss();
            }
        });
        forPlayDialog.show();
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    DerviceBean datebean;
    @Override
    public void onResponse(String sequest, int type) {

        Gson gson = new Gson();
        datebean =gson.fromJson(sequest,DerviceBean.class);
        dataBeanList.add(datebean.getData());
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onFailure(String exp) {

    }
}
