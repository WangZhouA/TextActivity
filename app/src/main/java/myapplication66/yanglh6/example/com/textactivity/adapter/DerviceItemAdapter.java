package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.DerviceBean;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */
public class DerviceItemAdapter extends BaseAdapter {
    private Context context;
    private List<DerviceBean.DataBean> mDatas;



    public  void  setNotily(List<DerviceBean.DataBean> mDatas){

        this.mDatas=mDatas;
        notifyDataSetChanged();
    }


    public DerviceItemAdapter(Context context, List<DerviceBean.DataBean> mDatas) {
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

        DerviceBean.DataBean dataBean = mDatas.get(position);
        MyViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dervice_item, null);
            viewHolder.lin_dervice = (LinearLayout) convertView.findViewById(R.id.lin_dervice);
            viewHolder.im_dervice = (ImageView) convertView.findViewById(R.id.im_dervice);
            viewHolder.name_dervice = (TextView) convertView.findViewById(R.id.dervice_name);
            viewHolder.state_dervice = (TextView) convertView.findViewById(R.id.dervice_state);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.name_dervice.setText(dataBean.getRqname());

        if (dataBean.getOnline()==1){

            viewHolder.state_dervice.setText(context.getResources().getString(R.string.online));

        } else if (dataBean.getOnline()==2){

            viewHolder.state_dervice.setText(context.getResources().getString(R.string.noline));
        }


        return  convertView;
    }


    class MyViewHolder  {

        LinearLayout lin_dervice;
        ImageView  im_dervice;
        TextView   name_dervice;
        TextView   state_dervice;


    }
}
