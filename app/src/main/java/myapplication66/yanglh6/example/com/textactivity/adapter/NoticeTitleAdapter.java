package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.NoticeTitleBean;
import myapplication66.yanglh6.example.com.textactivity.interfaces.IonGetNameLinstener;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeTitleCallback;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */

public   class NoticeTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NoticeTitleBean.DataBean> mDatas;
    NoticeTitleCallback callback;

    IonGetNameLinstener notifiyCallBack;

    public void setNotifiyCallBack(IonGetNameLinstener notifiyCallBack) {
        this.notifiyCallBack = notifiyCallBack;
    }

    public void setCallback(NoticeTitleCallback callback) {
        this.callback = callback;
    }

    public NoticeTitleAdapter(Context context, List<NoticeTitleBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_title_notice, viewGroup,false));


        return  holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final MyViewHolder holder = (MyViewHolder) viewHolder;
        final NoticeTitleBean.DataBean dataBean = mDatas.get(i);
        holder.tvValue.setText(dataBean.getValue());
        holder.linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                HttpUtils.getInstance(context).getSyncHttp(0x01, StringUtils.HTTP_SERVICE + StringUtils.NOTICE_TITLE + 16+"&name="+dataBean.getName(), new HttpRequestCallback() {
//                    @Override
//                    public void onResponse(String sequest, int type) {

                notifiyCallBack.onGetName(dataBean.getName());
                SPUtils.putString(context,"TName",dataBean.getName());
                SPUtils.putInt(context,"CODEID",dataBean.getId());
//
//                    }
//
//                    @Override
//                    public void onFailure(String exp) {
//
//                    }
//                });

                callback.noticeListener(dataBean.getId());

            }
        });

        if (dataBean.getState().contains("1")){

            holder.viewOne.setVisibility(View.VISIBLE);
            holder.linTitle.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {

            holder.viewOne.setVisibility(View.GONE);
            holder.linTitle.setBackgroundColor(Color.parseColor("#FAFAFA"));
        }



    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvValue;
        LinearLayout linTitle;
        View viewOne;
        public MyViewHolder(View view){
            super(view);
            tvValue =  view.findViewById(R.id.title_one);
            linTitle =  view.findViewById(R.id.lin_title);
            viewOne = view.findViewById(R.id.view_one);
        }
    }
}