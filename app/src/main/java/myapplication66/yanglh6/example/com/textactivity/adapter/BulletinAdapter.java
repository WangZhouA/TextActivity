package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.BullinXiangQingBean;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeMsgCallback;

/**
 * Created by 陈姣姣 on 2018/7/10.
 */
public class BulletinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    Context context;
    List<BullinXiangQingBean.DataBean>dataBeanList;


    NoticeMsgCallback callback;

    public void setCallback(NoticeMsgCallback callback) {
        this.callback = callback;
    }

    public BulletinAdapter(Context context, List<BullinXiangQingBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        BulletinAdapter.MyViewHolder holder = new BulletinAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_fenlei_xiangqing, viewGroup, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final BulletinAdapter.MyViewHolder holder = (BulletinAdapter.MyViewHolder) viewHolder;
        final    BullinXiangQingBean.DataBean dataBean = dataBeanList.get(i);

        holder.tvYiyuan .setText(dataBean.getHospitalName());
        holder.tvTime .setText(dataBean. getTitleOfTime());
        holder.tvGuancaPeople .setText(dataBean.getPageView()+"");
        holder.tvXiangqingMsg.setText(dataBean.getNoticeCountent());
        holder.tvTitleXiangqing.setText(dataBean.getNoticeTitle());
        String imgPath = StringUtils.HTTP_SERVICE+dataBean.getImgUrl();
        StringUtils.showImage(context,imgPath,R.mipmap.picaa,R.mipmap.picaa,holder.imPic);

        holder.lin_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callback.setIdListener(dataBean.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size()==0?0:dataBeanList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvYiyuan;
        TextView tvTime;
        TextView tvTitleXiangqing;
        TextView tvXiangqingMsg;
        TextView tvGuancaPeople;
        ImageView imPic;
        LinearLayout lin_msg;

        public MyViewHolder(View view) {
            super(view);
            tvYiyuan =view.findViewById(R.id.tv_yiyuan);
            tvTime =view.findViewById(R.id.tv_time);
            imPic =view.findViewById(R.id.im_pic);
            tvXiangqingMsg =view.findViewById(R.id.tv_xiangqing_msg);
            tvGuancaPeople =view.findViewById(R.id.tv_guanca_people);
            tvTitleXiangqing = view.findViewById(R.id.tv_title_xiangqing);
            lin_msg = view.findViewById(R.id.lin_msg);



        }
    }
}
