package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.TestSummaryBean;

/**
 * Created by 陈姣姣 on 2018/7/18.
 */
public class TrueAnswersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<TestSummaryBean.DataBean> mDatas;


    public void setNotify(List<TestSummaryBean.DataBean> mDatas) {
        this.mDatas = mDatas;

        notifyDataSetChanged();
    }


    public TrueAnswersAdapter(Context context, List<TestSummaryBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        TrueAnswersAdapter.MyViewHolder holder = new TrueAnswersAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.activity_true_answers, viewGroup, false));

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        TrueAnswersAdapter.MyViewHolder holder = (TrueAnswersAdapter.MyViewHolder) viewHolder;
         TestSummaryBean.DataBean dataBean = mDatas.get(i);

        holder.itemAnswersTitle.setText(dataBean.getQuestion());
        if (dataBean.getRealAnswer().contains("A")){
            holder.itemAnswers.setText(dataBean.getRealAnswer()+":"+dataBean.getAnswerA());
        }else if (dataBean.getRealAnswer().contains("B")){

            holder.itemAnswers.setText(dataBean.getRealAnswer()+":"+dataBean.getAnswerB());
        }else if (dataBean.getRealAnswer().contains("C")){

            holder.itemAnswers.setText(dataBean.getRealAnswer()+":"+dataBean.getAnswerC());
        }else if (dataBean.getRealAnswer().contains("D")){
            holder.itemAnswers.setText(dataBean.getRealAnswer()+":"+dataBean.getAnswerD());

        }

    }


    @Override
    public int getItemCount() {


        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView itemAnswersTitle;
         TextView itemAnswers;


        public MyViewHolder(View view) {
            super(view);

            itemAnswersTitle = view.findViewById(R.id.item_answers_title);
            itemAnswers = view.findViewById(R.id.item_answers_count);


        }
    }
}
