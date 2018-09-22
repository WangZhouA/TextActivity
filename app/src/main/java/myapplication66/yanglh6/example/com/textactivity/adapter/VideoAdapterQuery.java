package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.activity.BoFangActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.VideoQueryBean;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NoticeMsgCallback;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/7/26.
 */
public class VideoAdapterQuery extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<VideoQueryBean.DataBean.VideoInfosBean> mDatas;
    NoticeMsgCallback callback;


    public void setCallback(NoticeMsgCallback callback) {
        this.callback = callback;
    }

    public VideoAdapterQuery(Context context, List<VideoQueryBean.DataBean.VideoInfosBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        VideoAdapterQuery.MyViewHolder holder = new VideoAdapterQuery.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_soso, viewGroup, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final VideoAdapterQuery.MyViewHolder holder = (VideoAdapterQuery.MyViewHolder) viewHolder;
        final VideoQueryBean.DataBean.VideoInfosBean dataBean = mDatas.get(i);
        holder.tvValue.setText(dataBean.getTitle());
        holder.linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,String> maps =new ArrayMap<>();
                maps.put("id",dataBean.getId()+"");
                maps.put("userId", SPUtils.getInt(context, MyConstaints.USER_ID,0)+"");

                BigModle.getInstance(context).getData(context, maps, 0x01, new HttpRequestCallback() {
                    @Override
                    public void onResponse(String sequest, int type) {
                        context.startActivity(new Intent(context, BoFangActivity.class).putExtra("videoUrl",dataBean.getVideoUrl()));
                    }
                    @Override
                    public void onFailure(String exp) {

                    }
                }, StringUtils.ADDPEOPLE);


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
