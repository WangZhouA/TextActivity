package myapplication66.yanglh6.example.com.textactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import myapplication66.yanglh6.example.com.textactivity.activity.AnswerActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.DebitNoteActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.DoctorsPrescriptionActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.LoginActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.MsgActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.NoticeActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.RePassWordActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.StartConntionWifiActivity;
import myapplication66.yanglh6.example.com.textactivity.activity.VideoTutorialActivity;
import myapplication66.yanglh6.example.com.textactivity.base.BaseFragment;
import myapplication66.yanglh6.example.com.textactivity.entity.NoticeTitleBean;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.HttpUtils;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;
import myapplication66.yanglh6.example.com.textactivity.utils.MyDialog;
import myapplication66.yanglh6.example.com.textactivity.view.CircleImageView;

/**
 * Created by 陈姣姣 on 2018/6/19.
 */
public class MainLeftFragment extends BaseFragment implements HttpRequestCallback{


    @BindView(R.id.img_left)
    CircleImageView imgLeft;
    @BindView(R.id.left_name)
    TextView leftName;
    @BindView(R.id.left_phone)
    TextView leftPhone;
    @BindView(R.id.tv_left_networks)
    TextView tvLeftNetworks;
    @BindView(R.id.tv_left_recharge)
    TextView tvLeftRecharge;
    @BindView(R.id.tv_left_announcement)
    TextView tvLeftAnnouncement;
    @BindView(R.id.tv_left_video)
    TextView tvLeftVideo;
    @BindView(R.id.tv_left_topic)
    TextView tvLeftTopic;
    @BindView(R.id.tv_left_set)
    TextView tvLeftSet;
    @BindView(R.id.tv_left_logout)
    TextView tvLeftLogout;

    @BindView(R.id.tv_left_msg)
    TextView tv_leftMsg;


    @BindView(R.id.tv_left_doctors_prescription)
    TextView tv_left_doctors_prescription;


    Unbinder unbinder;

    @BindView(R.id.tv_count)
    TextView tvCount;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 0:
                    change(NoticeActivity.class,getActivity(),new Intent().putExtra("TName",TitleName).putExtra("CODEID",codeId),false);
                    break;
                case 1:
                    Toast.makeText(getActivity(), getResources().getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_left, null);
        unbinder = ButterKnife.bind(this, view);
        IntentFilter intentFilter = new IntentFilter("AHeader");
        intentFilter.addAction("con");
        intentFilter.addAction("CLEAR");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getActivity().unregisterReceiver(broadcastReceiver);

    }

    @OnClick({R.id.tv_left_doctors_prescription,R.id.tv_left_msg,R.id.img_left, R.id.tv_left_networks, R.id.tv_left_recharge, R.id.tv_left_announcement, R.id.tv_left_video, R.id.tv_left_topic, R.id.tv_left_set, R.id.tv_left_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {




            case R.id.tv_left_doctors_prescription:

                change(DoctorsPrescriptionActivity.class,getActivity(),new Intent(),false);
                break;
            case R.id.tv_left_msg:

                change(MsgActivity.class,getActivity(),new Intent(),false);
                break;




            case R.id.img_left:
                 if ( hasExternalStoragePermission(getActivity())==true) {
                     Intent intent = new Intent("Header");
                     getActivity().sendBroadcast(intent);
                 }else {

                     toast("您没有同意获取该权限就重新获取");
                 }
                break;
            case R.id.tv_left_networks:
                change(StartConntionWifiActivity.class,getActivity(),new Intent(),false);

                break;
            case R.id.tv_left_recharge:
                change(DebitNoteActivity.class,getActivity(),new Intent(),false);
                break;
            case R.id.tv_left_announcement:
                HttpUtils.getInstance(getActivity()).getSyncHttp(0x10, StringUtils.HTTP_SERVICE+StringUtils.NOTICE_TITLE+  SPUtils.getString(getActivity(),"YID",""),this);
                break;
            case R.id.tv_left_video:
                change(VideoTutorialActivity.class,getActivity(),new Intent(),false);
                break;
            case R.id.tv_left_topic:
                change(AnswerActivity.class,getActivity(),new Intent(),false);
                break;
            case R.id.tv_left_set:
                change(RePassWordActivity.class,getActivity(),new Intent(),false);

                break;
            case R.id.tv_left_logout:

                showDialog();

                break;
        }
    }



    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    private  boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }












    /**
     *  退出
     * */
    MyDialog builder;
    private void  showDialog(){

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.qiangzhi_xiaxian_layout,null);
        builder = new MyDialog(getActivity(), 0, 0, view, R.style.MyDialog);
        builder.setCancelable(false);
        Button btn_no_xian_ss= (Button) view.findViewById(R.id.btn_yes_xian_ss);
        Button  btn_yes_xiaxian_ss= (Button) view.findViewById(R.id.btn_no_xiaxian_ss);
        TextView tvTitle = (TextView) view.findViewById(R.id.for_tv_titles);
        TextView tvMsg = (TextView) view.findViewById(R.id.tv_msg);

        tvTitle.setText(getResources().getString(R.string.exit_login));
        tvMsg.setText(getResources().getString(R.string.exit_login_msg));
        tvMsg.setGravity(Gravity.CENTER);

        //取消按钮
        btn_no_xian_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        //确认按钮
        btn_yes_xiaxian_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                change(LoginActivity.class,getActivity(),new Intent().putExtra("LOUT","2"),false);

            }
        });

        builder.show();

    }

    NoticeTitleBean userBean;
    String TitleName="标题一";
    int codeId =0;
    @Override
    public void onResponse(String sequest, int type) {
        Gson gson =new Gson();
        if (type==0x10) {

            userBean = gson.fromJson(sequest, NoticeTitleBean.class);
            if (userBean.getCode()==1000){

                if (!TextUtils.isEmpty(SPUtils.getString(getActivity(),"TName",""))) {

                    TitleName =SPUtils.getString(getActivity(),"TName","");

                }else {

                    TitleName =userBean.getData().get(0).getName();

                }

                for (int i=0;i<userBean.getData().size();i++){

                        if (userBean.getData().get(i).getName().contains(TitleName)){

                            codeId = userBean.getData().get(i).getId();
                        }
                }
                SPUtils.putString(getActivity(),"TName",TitleName);
                SPUtils.putInt(getActivity(),"CODEID",codeId);

                handler.sendEmptyMessage(0);
            }else {
                handler.sendEmptyMessage(1);
            }
        }
    }

    @Override
    public void onFailure(String exp) {
        handler.sendEmptyMessage(1);

    }

    private BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();
            if (action.contains("AHeader")){
                String header =(StringUtils.HTTP_SERVICE+intent.getStringExtra("header")).trim().toString();
                Log.e("----->图片 header==",header);
                StringUtils.showImage(getActivity(),header,R.mipmap.head_portrait,R.mipmap.head_portrait,imgLeft);
                if (!TextUtils.isEmpty(intent.getStringExtra("name"))) {
                    leftName.setText(intent.getStringExtra("name") + "");
                }else {

                }
              if (!TextUtils.isEmpty(intent.getStringExtra("shenfen"))) {
                  leftPhone.setText(hideAllIdCardNum(intent.getStringExtra("shenfen")));
              }else {

              }
            }else if (action.contains("con")){
                int  count =intent.getIntExtra("count",0);
                tvCount.setVisibility(View.VISIBLE);
                tvCount.setText(count+"");
            }else if (action.contains("CLEAR")){

                tvCount.setVisibility(View.GONE);

            }
        }
    };

    public  String hideAllIdCardNum(String contentStr){

        Pattern pattern = Pattern.compile("(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)");
        Matcher matcher = pattern.matcher(contentStr);
        StringBuffer sb = new StringBuffer();
        try {
            while(matcher.find()) {
                String idCardStr = matcher.group();
                int len=idCardStr.length();
                if(len>=9){
                    idCardStr =  idCardStr.replaceAll("(.{"+(len<12?3:6)+"})(.*)(.{4})", "$1" + "****" + "$3");
                }
                matcher.appendReplacement(sb,idCardStr);
            }
            matcher.appendTail(sb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }





    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception

            // important to return false so the error placeholder can be placed

            Log.e("---错误",e.toString());
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (builder != null && builder.isShowing())
        {
            builder.dismiss();
        }
        getActivity().unregisterReceiver(broadcastReceiver);

    }









}
