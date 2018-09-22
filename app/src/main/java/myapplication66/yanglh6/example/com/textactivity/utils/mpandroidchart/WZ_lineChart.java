package myapplication66.yanglh6.example.com.textactivity.utils.mpandroidchart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.activity.ChartXiangQing;


/**
 * Created by 陈姣姣 on 2018/7/24.
 */
public class WZ_lineChart  {

    public  static WZ_lineChart instance ;


    private WZ_lineChart(){}
    public static WZ_lineChart getInstance(){
        if (instance == null) {
            instance = new WZ_lineChart();
        }
        return instance;
    }

    LineChart mChart;
    public void initChartView(LineChart mChart, final String[] valuesNew,int MAXANDMIN) {
        this.mChart=mChart;
        //是否可以缩放
        mChart.setScaleEnabled(true);
        //集双指缩放
        mChart.setPinchZoom(true);
        mChart.setViewPortOffsets(0, 0, 150, 180);
        //增加X轴最左边与Y轴的距离
        mChart.setDragOffsetX(30);
        mChart.setBackgroundColor(Color.parseColor("#FFFFFF"));
        //设置背景颜色
        mChart.setDrawGridBackground(true);
        //设置表格颜色
        mChart.setGridBackgroundColor(Color.parseColor("#FFFFFF"));
//        mChart.getAxisRight().setEnabled(true);
        //设置一个描述的文本出现在右下角的图
        mChart.setDescription(null);


        float scaleX = mChart.getScaleX();
        if (scaleX == 1) {
            BarLineChartTouchListener barLineChartTouchListener = (BarLineChartTouchListener) mChart.getOnTouchListener();
            barLineChartTouchListener.stopDeceleration();
            mChart.fitScreen();
            if (valuesNew.length>=10) {
                mChart.zoomToCenter(2, 1f);
            }
            Log.e("----->scaleXAA", scaleX + "");
        } else {
            //如果比例尺已经发生改变就不再改变了
            Log.e("----->scaleXBBB", scaleX + "");
        }


        //隐藏图例
        mChart.getLegend().setEnabled(false);
        //设置X轴
        XAxis xAxis = mChart.getXAxis();
        //设置X轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.parseColor("#FF0000"));
        xAxis.setDrawGridLines(false);
        //不显示X坐标轴上的线
        xAxis.setYOffset(8f);
        xAxis.setTextSize(11f);

        xAxis.setLabelCount(20);
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大x轴标签重绘

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < 0 || index >= valuesNew.length) {
                    return "";
                } else {
                    return valuesNew[index];
                }
            }
        });

        YAxis rightAxis = mChart.getAxisLeft();
        rightAxis.setTextColor(Color.parseColor("#CDCDCD"));
        rightAxis.setGridColor(Color.parseColor("#CDCDCD"));
        rightAxis.setGridLineWidth(1f);
        rightAxis.setDrawZeroLine(true); // draw a zero line

        rightAxis.setLabelCount(5,true); //显示格数

        rightAxis.setTextSize(15);
        //不显示Y坐标轴上的线
        rightAxis.setDrawAxisLine(false);
        //设置Y坐标显示在右边
//        rightAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        rightAxis.setYOffset(10f);

//        rightAxis.setAxisMinimum(0f);
//        rightAxis.setZeroLineColor(Color.parseColor("#FFFFFF"));
//        rightAxis.setAxisMaximum(2500f);


        //设置动画效果
        YAxis leftAxis = mChart.getAxisRight();
        //设置图表左边的y轴禁用
        leftAxis.setEnabled(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setLabelCount(5,true); //显示格数
        leftAxis.setTextColor(Color.parseColor("#CDCDCD"));
        leftAxis.setTextSize(15);

        if (MAXANDMIN==0) {
            rightAxis.setAxisMinimum(-1000f);
            rightAxis.setAxisMaximum(2500f);
            rightAxis.setZeroLineColor(Color.parseColor("#FFFFFF"));

            leftAxis.setAxisMinimum(-1000f);
            leftAxis.setAxisMaximum(2500f);
            leftAxis.setZeroLineColor(Color.parseColor("#FFFFFF"));



        }else if (MAXANDMIN==1){

            rightAxis.setAxisMinimum(0f);
            rightAxis.setAxisMaximum(2000f);

            leftAxis.setAxisMinimum(0f);
            leftAxis.setAxisMaximum(2000f);

        }else if (MAXANDMIN==2){

            rightAxis.setAxisMinimum(0f);
            rightAxis.setAxisMaximum(280f);
            leftAxis.setAxisMinimum(0f);
            leftAxis.setAxisMaximum(280f);
        }else if (MAXANDMIN==3){

            rightAxis.setAxisMinimum(0f);
            rightAxis.setAxisMaximum(300f);
            leftAxis.setAxisMinimum(0f);
            leftAxis.setAxisMaximum(300f);
        }





        //保证Y轴从0开始，不然会上移一点
//        mChart.setVisibleXRangeMinimum(1)  ; //x轴可显示的坐标范围
//        mChart.animateY(2000, Easing.EasingOption.Linear);
//        mChart.animateX(2000, Easing.EasingOption.Linear);
//


        //保证Y轴从0开始，不然会上移一点
//        mChart.setVisibleXRangeMinimum(1)  ; //x轴可显示的坐标范围
//        mChart.animateY(2000, Easing.EasingOption.Linear);
//        mChart.animateX(2000, Easing.EasingOption.Linear);
//
        mChart.invalidate();
        mChart.getLegend().setEnabled(false);
    }

    public void initData(ArrayList<Entry> values1 , ArrayList<Entry> values2, ArrayList<Entry> values3, final Context context, LineChart mChart, int flag) {

        BarLineChartTouchListener barLineChartTouchListener = (BarLineChartTouchListener) mChart.getOnTouchListener();
        // 添加折线
        List<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(values1, "");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(true);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        LineDataSet lineDataSet1 = new LineDataSet(values2, "");
        lineDataSet1.setLineWidth(2f);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawValues(true);
        lineDataSet1.setDrawFilled(false);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineDataSet lineDataSet2 = new LineDataSet(values3, "");
        lineDataSet2.setLineWidth(2f);
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawValues(true);
        lineDataSet2.setDrawFilled(false);
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);



        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(2.5f); // 线宽
        lineDataSet.setCircleSize(4f);// 显示的圆形大小
        lineDataSet.setColor(Color.parseColor("#20A0FF"));// 折线显示颜色
        lineDataSet.setCircleColor(Color.parseColor("#20A0FF"));// 圆形折点的颜色
        lineDataSet.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setValueTextColor(Color.parseColor("#20A0FF")); //数值显示的颜色
        lineDataSet.setValueTextSize(6f);     //数值显示的大小
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
//        lineDataSet.setHighlightBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.statistic_highlight));
        //用y轴的集合来设置参数
        lineDataSet1.setLineWidth(2.5f); // 线宽
        lineDataSet1.setCircleSize(4f);// 显示的圆形大小
        lineDataSet1.setColor(Color.parseColor("#03CA6B"));// 折线显示颜色
        lineDataSet1.setCircleColor(Color.parseColor("#03CA6B"));// 圆形折点的颜色
        lineDataSet1.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        lineDataSet1.setHighlightEnabled(true);
        lineDataSet1.setValueTextColor(Color.parseColor("#03CA6B")); //数值显示的颜色
        lineDataSet1.setValueTextSize(6f);     //数值显示的大小
        lineDataSet1.setDrawHorizontalHighlightIndicator(true);
//        lineDataSet1.setHighlightBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.statistic_highlight));


        lineDataSet2.setLineWidth(2.5f); // 线宽
        lineDataSet2.setCircleSize(4f);// 显示的圆形大小
        lineDataSet2.setColor(Color.parseColor("#FF821D"));// 折线显示颜色
        lineDataSet2.setCircleColor(Color.parseColor("#FF821D"));// 圆形折点的颜色
        lineDataSet2.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        lineDataSet2.setHighlightEnabled(true);
        lineDataSet2.setValueTextColor(Color.parseColor("#FF821D")); //数值显示的颜色
        lineDataSet2.setValueTextSize(6f);     //数值显示的大小
        lineDataSet2.setDrawHorizontalHighlightIndicator(true);

        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        // 绘制折线
        LineData data = new LineData(dataSets);
        mChart.setData(data);
        if (flag!=2) {
            float scaleX = mChart.getScaleX();
            if (scaleX == 1) {
                if (values1.size()>=10) {
                    mChart.zoomToCenter(2, 1f);
                }else {
                    barLineChartTouchListener.stopDeceleration();
                    mChart.fitScreen();
                }
                Log.e("----->scaleXAA", scaleX + "");
            } else {
                //如果比例尺已经发生改变就不再改变了
                if (values1.size()>=10) {

                }else {
                    barLineChartTouchListener.stopDeceleration();
                    mChart.fitScreen();
                }
                Log.e("----->scaleXBBB", scaleX + "");
            }
        }
        mChart.invalidate();

    }
    public void initDataDan(ArrayList<Entry> values1 , final Context context, LineChart mChart , int color_wz , int TYPE, int flag) {



        BarLineChartTouchListener barLineChartTouchListener = (BarLineChartTouchListener) mChart.getOnTouchListener();

        // 添加折线
        List<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(values1, "");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(true);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(2.5f); // 线宽
        lineDataSet.setCircleSize(4f);// 显示的圆形大小
        lineDataSet.setColor(color_wz);// 折线显示颜色
        lineDataSet.setCircleColor(color_wz);// 圆形折点的颜色
        lineDataSet.setHighLightColor(Color.YELLOW); // 高亮的线的颜色
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setValueTextColor(color_wz); //数值显示的颜色
        lineDataSet.setValueTextSize(6f);     //数值显示的大小
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        dataSets.add(lineDataSet);
        // 绘制折线
        LineData data = new LineData(dataSets);
        mChart.setData(data);

        if (flag!=2) {
            float scaleX = mChart.getScaleX();
            if (scaleX == 1) {
                if (values1.size()>=10) {
                    mChart.zoomToCenter(2, 1f);
                }else {
                    barLineChartTouchListener.stopDeceleration();
                    mChart.fitScreen();
                }
                Log.e("----->scaleXAA", scaleX + "");
            } else {
                //如果比例尺已经发生改变就不再改变了
                if (values1.size()>=10) {

                }else {
                    barLineChartTouchListener.stopDeceleration();
                    mChart.fitScreen();
                }
                Log.e("----->scaleXBBB", scaleX + "");
            }
        }

        mChart.invalidate();
        if (TYPE == 1) {
            mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    Log.e("----->x", e.getX() + "");
                    Log.e("----->Y", e.getY() + "");
                    context.startActivity(new Intent(context, ChartXiangQing.class).putExtra("x",(int)e.getX()));

                }

                @Override
                public void onNothingSelected() {
                    Log.e("----->", "2");
                }
            });
        }else {
        }
    }
}
