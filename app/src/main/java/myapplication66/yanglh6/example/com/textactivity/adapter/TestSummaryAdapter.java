package myapplication66.yanglh6.example.com.textactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.R;
import myapplication66.yanglh6.example.com.textactivity.entity.TestSummaryBean;
import myapplication66.yanglh6.example.com.textactivity.interfaces.NotifiyCallBack;

/**
 * Created by 陈姣姣 on 2018/7/11.
 */
public class TestSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<TestSummaryBean.DataBean> mDatas;

    String [] daAn ;

    String[] chooseAnswer ={"A", "B", "C", "D"};

    NotifiyCallBack notifiyCallBack;
    boolean submitClickAble = false;//点击按钮是否可被点击
    HashSet<Integer> noAnswers = new HashSet<>();//未回答的题目



    public void setNotifiyCallBack(NotifiyCallBack notifiyCallBack) {
        this.notifiyCallBack = notifiyCallBack;
    }


    public void setNotify( List<TestSummaryBean.DataBean> mDatas){
        this.mDatas =mDatas;
        createNoAnswerList(mDatas == null ? 0:mDatas.size());
        notifyDataSetChanged();
    }


    public TestSummaryAdapter(Context context, List<TestSummaryBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        createNoAnswerList(mDatas == null ? 0:mDatas.size());
    }

    //创建未回答的题目的结合
    private void createNoAnswerList(int listSize){
        noAnswers.clear();
        for(int i=0; i< listSize; i++){
            noAnswers.add(i);
        }
        Log.e("---noAnswers.size()",noAnswers.size()+"");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        TestSummaryAdapter.MyViewHolder holder = new TestSummaryAdapter.MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_test_summary, viewGroup, false));

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final
    int i) {

        final TestSummaryAdapter.MyViewHolder holder = (TestSummaryAdapter.MyViewHolder) viewHolder;
        final TestSummaryBean.DataBean dataBean = mDatas.get(i);



        holder.tv_TestTitle.setText("Q"+(i+1)+"."+dataBean.getQuestion());
        holder.radioButtonA.setText("A."+dataBean.getAnswerA());
        holder.radioButtonB.setText("B."+dataBean.getAnswerB());
        holder.radioButtonC.setText("C."+dataBean.getAnswerC());
        holder.radioButtonD.setText("D."+dataBean.getAnswerD());





        if (holder.RadioGroup.getTag()==null){



            holder.RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    int position = (int) radioGroup.getTag();
                    RadioButton radioButton = radioGroup.findViewById(checkedId);
                    if(radioButton == null || !radioButton.isChecked())return;
                    if(noAnswers.contains(position)){
                        noAnswers.remove(position);
                    }
                    int checkPosition = radioGroup.indexOfChild(radioButton);
                    if (checkPosition<0){
                        return;
                    }
                    Log.d("---adapter_click","click checkPosition:"+checkPosition+"== position =="+position+" checkId="+checkedId);
                    mDatas.get(position).setCheck(chooseAnswer[checkPosition]);
                    if(noAnswers.isEmpty() != submitClickAble){
                        //可点击状态改变回调
                        submitClickAble = !submitClickAble;
                        notifiyCallBack.notifiy();

                    }
                }
            });
        }
        holder.RadioGroup.setTag(i);
        setItemChecked(holder.RadioGroup);



    }

    private void setItemChecked(RadioGroup radioGroup){
        if(radioGroup == null || radioGroup.getTag() == null)return;
        int position = (int) radioGroup.getTag();
        String check = mDatas.get(position).getIsCheck();
        if(radioGroup == null)return;
        int checkedPosition = -1;
        if(!TextUtils.isEmpty(check)){
            for(int i=0; i< chooseAnswer.length; i++){
                if(chooseAnswer[i].equals(check)){
                    checkedPosition = i;
                    break;
                }
            }
        }
        //参考：https://www.jianshu.com/p/b346610a6bbd
        int checkId = -1;
        if(checkedPosition >= 0){
            View childView = radioGroup.getChildAt(checkedPosition);
            if(childView instanceof RadioButton){
                RadioButton radioButton = (RadioButton) childView;
                checkId = radioButton.getId();
            }
        }
        radioGroup.check(checkId);
        Log.d("---adapter_item",String.format("position:%s check:%s checkPisition:%s checkId:%s", position, check, checkedPosition, checkId));

    }

    /**是否还有未回答的题目*/
    public boolean hasNoAnswer(){
        return !noAnswers.isEmpty();
    }


    /**
     *  获取答案
     * */
    public   String[] getAnswers(){
        if(mDatas == null)return null;
        String [] answers =new String[mDatas.size()];
        for (int i =0; i<answers.length;i++){
            answers[i]=mDatas.get(i).getIsCheck();

        }
        return answers;
    }






    @Override
    public int getItemCount() {

        daAn = new String[mDatas.size()];
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TestTitle;
        RadioButton radioButtonA;
        RadioButton radioButtonB;
        RadioButton radioButtonC;
        RadioButton radioButtonD;
        RadioGroup RadioGroup;

        public MyViewHolder(View view) {
            super(view);

            tv_TestTitle = view.findViewById(R.id.tv_test_summary);
            radioButtonA=view.findViewById(R.id.rb_a);
            radioButtonB=view.findViewById(R.id.rb_b);
            radioButtonC=view.findViewById(R.id.rb_c);
            radioButtonD=view.findViewById(R.id.rb_d);
            RadioGroup=view.findViewById(R.id.RadioGroup_btn);

        }
    }
}
