package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.activity.BulletinActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.NoticeTitleBean;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeMsgCallback;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */

public   class NoticeMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NoticeTitleBean.DataBean> mDatas;
    NoticeMsgCallback  callback;


    public void setCallback(NoticeMsgCallback callback) {
        this.callback = callback;
    }

    public NoticeMsgAdapter(Context context, List<NoticeTitleBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_title_notice, viewGroup, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final MyViewHolder holder = (MyViewHolder) viewHolder;
        final NoticeTitleBean.DataBean dataBean = mDatas.get(i);
        holder.tvValue.setText(dataBean.getValue());
        holder.linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               context.startActivity(new Intent(context, BulletinActivity.class).putExtra("ID",dataBean.getId()).putExtra("TITLE",dataBean.getValue()));

            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvValue;
        LinearLayout linTitle;

        public MyViewHolder(View view) {
            super(view);
            tvValue = view.findViewById(R.id.title_one);
            linTitle = view.findViewById(R.id.lin_title);
        }
    }
}