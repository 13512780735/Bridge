package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.jiangfeng.chart.charts.BarChart;
import com.jiangfeng.chart.charts.LineChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnClickColumnListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 水位表
 */
public class WaterActivity extends BaseActivity {
    @BindView(R.id.main_lineChart)
    LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        setBackView();
        initUI();
        initChart(mLineChart);
    }

    private void initChart(LineChart lineChart) {
        int size = 6;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10.1 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("SEP" + i);
        }
        ChartData<Double> chartData = new ChartData<>("营收报表", xAxisList, yList);
        //图表外边距
        lineChart.setPadding(25);
       // lineChart.setShowXScaleLine(true);
        lineChart.setShowYScaleLine(true);
        lineChart.setXScaleSize(6);
        lineChart.setYScaleSize(6);
        lineChart.setZoom(true);
        lineChart.setLineModel(LineChart.CURVE_MODEL);
        lineChart.setShowPointValue(true);
        lineChart.setChartData(chartData);
    }


    private void initUI() {
        setTitle("水位表", getResources().getColor(R.color.title_color));
    }
}
