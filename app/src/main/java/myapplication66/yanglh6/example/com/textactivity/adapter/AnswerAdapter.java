package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.FenLei;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeTitleCallback;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */
public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FenLei.DataBean> mDatas;
    NoticeTitleCallback callback;

    public void setCallback(NoticeTitleCallback callback) {
        this.callback = callback;
    }

    public AnswerAdapter(Context context, List<FenLei.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        AnswerAdapter.MyViewHolder holder = new AnswerAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_answer_the_questions, viewGroup, false));

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final AnswerAdapter.MyViewHolder holder = (AnswerAdapter.MyViewHolder) viewHolder;
        final FenLei.DataBean dataBean = mDatas.get(i);
        holder.tvValue.setText(dataBean.getName());
        holder.linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callback.noticeListener(dataBean.getId());

            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvValue;
        ImageView linTitle;

        public MyViewHolder(View view) {
            super(view);
            tvValue = view.findViewById(R.id.tv_name);
            linTitle = view.findViewById(R.id.im_One);
        }
    }
}
