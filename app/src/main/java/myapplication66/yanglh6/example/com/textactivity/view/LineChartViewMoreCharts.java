package myapplication66.yanglh6.example.com.textactivity.view;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by 陈姣姣 on 2018/6/26.
 */
public class LineChartViewMoreCharts {




    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<PointValue> mPointValues1 = new ArrayList<PointValue>();
    private List<PointValue> mPointValues2 = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues =new ArrayList<>();

    LineChartView lineChart;
    String [] date;
    int [] score;
    int [] score1;
    int [] score2;
    int [] dataY;

    public LineChartViewMoreCharts(LineChartView lineChart, String[] date, int[] score, int[] score1,int [] score2,int [] dataY ) {
        this.lineChart = lineChart;
        this.date = date;
        this.score = score;
        this.score1 = score1;
        this.score2 = score2;
        this.dataY = dataY;
        getAxisXLables();// 获取x轴的标注
        getAxisPoints();// 获取坐标点
        getAxisPoints1();
        getAxisPoints2();
        initLineChart();// 初始化



    }

    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#249DFF")); // 折线的颜色
        Line line1 = new Line(mPointValues1).setColor(Color.parseColor("#0ACA6C")); // 折线的颜色
        Line line2 = new Line(mPointValues2).setColor(Color.parseColor("#FF7F18")); // 折线的颜色

        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);// 折线图上每个数据点的形状 这里是圆形 （有三种
        // ：ValueShape.SQUARE
        // ValueShape.CIRCLE
        line.setCubic(false);// 曲线是否平滑

        // line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);// 是否填充曲线的面积
        line.setHasLabels(true);// 曲线的数据坐标是否加上备注
        // line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);// 是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示

        line1.setShape(ValueShape.CIRCLE);// 折线图上每个数据点的形状 这里是圆形 （有三种
        line1.setCubic(false);
        line1.setFilled(false);
        line1.setHasLabels(true);
        line1.setHasLines(true);
        line1.setHasPoints(true);

        line2.setShape(ValueShape.CIRCLE);// 折线图上每个数据点的形状 这里是圆形 （有三种
        line2.setCubic(false);
        line2.setFilled(false);
        line2.setHasLabels(true);
        line2.setHasLines(true);
        line2.setHasPoints(true);
        lines.add(line);
        lines.add(line1);
        lines.add(line2);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        // 坐标轴
        Axis axisX = new Axis(); // X轴
        axisX.setHasTiltedLabels(true); // X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
        // axisX.setTextColor(Color.WHITE); //设置字体颜色
        axisX.setTextColor(Color.parseColor("#D6D6D9"));// 灰色

        // axisX.setName("未来几天的天气"); //表格名称
        axisX.setTextSize(11);// 设置字体大小
        axisX.setMaxLabelChars(1); // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues); // 填充X轴的坐标名称
        axisX.setHasTiltedLabels(false);
        data.setAxisXBottom(axisX); // x 轴在底部
        // data.setAxisXTop(axisX); //x 轴在顶部

        Axis axisY = new Axis(); // Y轴
        axisY.setTextSize(11);// 设置字体大小
        axisY.setHasLines(true); // x 轴分割线


        for(int i = 0; i < dataY.length; i++){
            AxisValue value = new AxisValue(dataY[i]);
            mAxisYValues.add(value);
        }
        axisY.setValues(mAxisYValues);

        data.setValueLabelBackgroundEnabled(false);
//        如果想要原来的效果可以不用这两句话，我的显示的是透明的
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);
        data.setValueLabelsTextColor(Color.parseColor("#249DFF"));
//        data.setAxisYLeft(axisY);  //Y轴设置在左边
        data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(false);
        lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        lineChart.setMaxZoom((float) 4);//缩放比例
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);





        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);
    }

    /**
     * X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints1() {
        for (int i = 0; i < score1.length; i++) {
            mPointValues1.add(new PointValue(i, score1[i]));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints2() {
        for (int i = 0; i < score2.length; i++) {
            mPointValues2.add(new PointValue(i, score2[i]));
        }
    }
}
