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
 * Created by 陈姣姣 on 2018/6/25.
 */
public class LineChartView_WZ  {

    private LineChartView lineChart;
    String[] date;

    int[] score;
    int [] dataY;


    public LineChartView_WZ(LineChartView lineChart, String[] x, int[] score ,int [] dataY) {
        this.lineChart = lineChart;
        this.date = x;
        this.score = score;
        this.dataY=dataY;



        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }

    private List<PointValue> mPointValues = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<>();
    private List<AxisValue> mAxisYValues =new ArrayList<>();


    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(){




        Line line = new Line(mPointValues).setColor(Color.parseColor("#249DFF"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        // line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);// 是否填充曲线的面积
        line.setHasLabels(true);// 曲线的数据坐标是否加上备注
        // line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);// 是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示



        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);


        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
//	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
        for (int i=0; i<date.length;i++){
            if (i==date.length-1){


                axisX.setTextColor(Color.parseColor("#717171"));//灰色
            }else {
                axisX.setTextColor(Color.parseColor("#242424"));//灰色
            }
        }

//	    axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(1); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(false); //x 轴分割线


        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setHasLines(true); //y 轴分割线
        axisY.setTextSize(11);//设置字体大小
//        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setTextColor(Color.parseColor("#717171"));  //设置字体颜色
        data.setAxisYRight(axisY);  //y轴设置在右边

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
        /**注：下面的7，10只是代表一个数字去类比而已
         * 尼玛搞的老子好辛苦！！！见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
         * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
         * 若设置axisX.setMaxLabelChars(int count)这句话,
         * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
         刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
         若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
         * 并且Y轴是根据数据的大小自动设置Y轴上限
         * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;

//        Viewport viewport =new Viewport(0,  lineChart.getMaximumViewport().height()*1.25f, numValues > 5 ? 5 : numValues, 0);
//        lineChart.setCurrentViewport(viewport);
//        lineChart.moveTo(0, 0);



        lineChart.setCurrentViewport(v);
    }

    /**
     * X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));

        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(){
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }


}

