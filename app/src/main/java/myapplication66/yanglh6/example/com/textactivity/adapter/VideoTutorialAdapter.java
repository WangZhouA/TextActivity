package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.activity.BoFangActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MyConstaints;
import myapplication66.yanglh6.example.com.textactivity.entity.VideoBean;
import myapplication66.yanglh6.example.com.textactivity.http.BigModle;
import myapplication66.yanglh6.example.com.textactivity.http.HttpRequestCallback;
import myapplication66.yanglh6.example.com.textactivity.http.StringUtils;
import myapplication66.yanglh6.example.com.textactivity.sharedpreferences.SPUtils;

/**
 * Created by 陈姣姣 on 2018/7/18.
 */
public class VideoTutorialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private List<VideoBean.DataBean.VideoInfosBean> mDatas;


    //俩种不同的类型

    public static  final  int JINGXUAN= 0X01;
    public static  final  int PUTONG= 0X02;



    public VideoTutorialAdapter(Context context, List<VideoBean.DataBean.VideoInfosBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        VideoTutorialAdapter.MyViewHolder holder = new VideoTutorialAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_video
                , viewGroup, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        VideoTutorialAdapter.MyViewHolder holder = (VideoTutorialAdapter.MyViewHolder) viewHolder;
        final VideoBean.DataBean.VideoInfosBean dataBean = mDatas.get(i);

        StringUtils.showImage(context,StringUtils.HTTP_SERVICE+dataBean.getVideoUrl(),R.mipmap.bitmp,R.mipmap.bitmp,holder.im_video);
        holder.title_video.setText(dataBean.getTitle());
        holder.people_video.setText(dataBean.getPartake()+context.getResources().getString(R.string.people));

//        http://www.999d.com/video/98193.html?source=baidu_open


        holder.im_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,String>maps =new ArrayMap<>();
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
                },StringUtils.ADDPEOPLE);


            }
        });

    }





    @Override
    public int getItemCount() {
        return mDatas.size()==0?0:mDatas.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView im_video;
        TextView  title_video;
        TextView  people_video;

        public MyViewHolder(View view) {
            super(view);

            im_video =view.findViewById(R.id.im_video);
            title_video=view.findViewById(R.id.title_video);
            people_video =view.findViewById(R.id.people_video);

        }
    }
}
