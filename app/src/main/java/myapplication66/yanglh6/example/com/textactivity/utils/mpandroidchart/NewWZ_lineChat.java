package myapplication66.yanglh6.example.com.textactivity.utils.mpandroidchart;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈姣姣 on 2018/8/15.
 */


/**
 * Created by 陈姣姣 on 2018/7/24.
 */
public class NewWZ_lineChat {

    public static NewWZ_lineChat instance;


    private NewWZ_lineChat() {
    }

    public static NewWZ_lineChat getInstance() {
        if (instance == null) {
            instance = new NewWZ_lineChat();
        }
        return instance;
    }

    public void initChartView(LineChart chart,String [] xValue) {
        // 不显示数据描述
        chart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        chart.setNoDataText("暂无数据");
        // 不显示表格颜色
        chart.setDrawGridBackground(false);
        // 不可以缩放
        chart.setScaleEnabled(true);

        chart.setBackgroundColor(Color.parseColor("#FFFFFF"));
        //设置背景颜色
        chart.setDrawGridBackground(true);
        //设置表格颜色
        chart.setGridBackgroundColor(Color.parseColor("#FFFFFF"));


        // 不显示y轴右边的值
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(true);
        // 显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.parseColor("#555555"));
        xAxis.setTextSize(10);
        xAxis.setDrawGridLines(false);
        xAxis.setGridColor(Color.parseColor("#DCDDDD"));
        // 设置x轴数据偏移量
//        xAxis.setYOffset(-12);
//        xAxis.setLabelCount(xValue.length,true);
        xAxis.setLabelCount(7,false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
        yAxis.setDrawGridLines(true);
        yAxis.setTextColor(Color.parseColor("#555555"));
        yAxis.setTextSize(10);
        // 设置y轴数据偏移量
//        yAxis.setXOffset(30);
//        yAxis.setYOffset(-3);
//        yAxis.setAxisMinimum(0);
        chart.invalidate();

    }


    /**
     * 更新图表
     *
     * @param chart  图表
     * @param values 数据
     */
    public  void notifyDataSetChanged(LineChart chart,Context context, List<Entry> values, final String [] xValuesProcess,int color ,int TYPE) {
        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < 0 || index >= xValuesProcess(xValuesProcess).length) {
                    return "";
                } else {
                    return xValuesProcess(xValuesProcess)[index];
                }
            }
        });


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMaximum(2000);
        leftAxis.setAxisMinimum(0);
        leftAxis.setLabelCount(5, true);
        chart.invalidate();
        setChartData(chart,context, values,xValuesProcess,color,  TYPE);
    }



    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public static void setChartData(LineChart chart, final Context context, List<Entry> values,String [] xValuesProcess, int color_wz , int TYPE) {
        LineDataSet lineDataSet;
        List<ILineDataSet> dataSets = new ArrayList<>();

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "");
            lineDataSet.setLineWidth(2);
            // 设置曲线颜色
            lineDataSet.setColor(color_wz);
            // 设置平滑曲线
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 不显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(true);
            // 不显示定位线
            lineDataSet.setHighlightEnabled(false);



            //用y轴的集合来设置参数
            lineDataSet.setLineWidth(2.5f); // 线宽
            lineDataSet.setCircleSize(4f);// 显示的圆形大小
            lineDataSet.setColor(color_wz);// 折线显示颜色
            lineDataSet.setCircleColor(color_wz);// 圆形折点的颜色
            lineDataSet.setHighLightColor(Color.YELLOW); // 高亮的线的颜色
            lineDataSet.setHighlightEnabled(true);
            lineDataSet.setValueTextColor(color_wz); //数值显示的颜色
            lineDataSet.setValueTextSize(8f);     //数值显示的大小
            lineDataSet.setDrawHorizontalHighlightIndicator(true);
            dataSets.add(lineDataSet);
            // 绘制折线
            LineData data = new LineData(dataSets);
            chart.setData(data);

            float scaleX = chart.getScaleX();
            if (scaleX == 1.0) {
                if (xValuesProcess(xValuesProcess).length>=10) {
                    chart.zoomToCenter(4, 1f);
                }
                Log.e("----->scaleXAA", scaleX + "");
            } else {
                //如果比例尺已经发生改变就不再改变了
                Log.e("----->scaleXBBB", scaleX + "");
            }
        }

        chart.invalidate();

        if (TYPE == 1) {
//            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//                @Override
//                public void onValueSelected(Entry e, Highlight h) {
//                    Log.e("----->x", e.getX() + "");
//                    Log.e("----->Y", e.getY() + "");
//                    context.startActivity(new Intent(context, ChartXiangQing.class).putExtra("x",(int)e.getX()));
//                }
//                @Override
//                public void onNothingSelected() {
//                    Log.e("----->", "2");
//                }
//            });
        }else {
        }
    }


    /**
     * x轴数据处理
     *
     * @return x轴数据
     */
    private static String[] xValuesProcess(String[] xValuesProcess) {
        return xValuesProcess;

    }

}
