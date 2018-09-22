package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.activity.MsgXiangQingActivity;
import myapplication66.yanglh6.example.com.textactivity.entity.MsgBean;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */
public class MsgAdapter extends BaseAdapter {
    private Context context;
    private List<MsgBean.DataBean> mDatas;



    public  void  setNotily(List<MsgBean.DataBean> mDatas){

        this.mDatas=mDatas;
        notifyDataSetChanged();
    }


    public MsgAdapter(Context context, List<MsgBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size()==0?0: mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MsgBean.DataBean dataBean = mDatas.get(position);
        MyViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_msg, null);
            viewHolder.lin_msg = (RelativeLayout) convertView.findViewById(R.id.lin_msg);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(dataBean.getNotificationContent());
        viewHolder.time.setText(dataBean.getCreationTime());
        viewHolder.lin_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, MsgXiangQingActivity.class).putExtra("IDS",dataBean.getId()));
            }
        });




        return  convertView;
    }


    class MyViewHolder  {

        RelativeLayout lin_msg;
        TextView   name;
        TextView   time;


    }
}
