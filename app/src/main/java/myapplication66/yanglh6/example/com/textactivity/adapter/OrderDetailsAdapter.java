package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.OrderDetailsBean;
import myapplication66.yanglh6.example.com.textactivity.interfaces.OnorderListener;

/**
 * Created by 陈姣姣 on 2018/6/29.
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<OrderDetailsBean.DataBean> mDatas;

    OnorderListener noticeTitleCallback;


    public OrderDetailsAdapter(Context context, List<OrderDetailsBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public void setNoticeTitleCallback(OnorderListener noticeTitleCallback) {
        this.noticeTitleCallback = noticeTitleCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        OrderDetailsAdapter.MyViewHolder holder = new OrderDetailsAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_order_details, viewGroup, false));

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final OrderDetailsAdapter.MyViewHolder holder = (OrderDetailsAdapter.MyViewHolder) viewHolder;
        final OrderDetailsBean.DataBean dataBean = mDatas.get(i);

        holder.tvMethod_of_payment.setText(dataBean.getType());
        holder.tvPayment_time.setText(dataBean.getExpiryTime());
        holder.tvPay_the_amount.setText(dataBean.getAmount());

        holder.rlOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeTitleCallback.orderListener(dataBean.getSerialNumber());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlOrder;
        TextView tvMethod_of_payment;
        TextView tvPayment_time;
        TextView tvPay_the_amount;


        public MyViewHolder(View view) {
            super(view);
            rlOrder = view.findViewById(R.id.rl_order);
            tvMethod_of_payment = view.findViewById(R.id.tv_Method_of_payment);
            tvPayment_time = view.findViewById(R.id.tv_Payment_time);
            tvPay_the_amount = view.findViewById(R.id.tv_Pay_the_amount);

        }
    }
}
