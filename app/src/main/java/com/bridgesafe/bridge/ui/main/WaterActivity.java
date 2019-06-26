package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.jiangfeng.chart.charts.BarChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnClickColumnListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 水位表
 */
public class WaterActivity extends BaseActivity {
    @BindView(R.id.main_barChart)
    BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        setBackView();
        initUI();
        initBarChart(mBarChart);
    }

    private void initBarChart(BarChart barChart) {
        int size = 5;
        List<Double> yList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            yList.add(10 + Math.random() * 100);
        }
        List<String> xAxisList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            xAxisList.add("SEP" + i);
        }
        final ChartData<Double> chartData = new ChartData<>("营收报表", xAxisList, yList);
        //图表外边距
        barChart.setPadding(30);
        // barChart.setShowXScaleLine(true);
        barChart.setShowYScaleLine(true);
        barChart.setXScaleSize(4);
        barChart.setYScaleSize(8);
        barChart.setShowPointValue(true);
        barChart.setShowLine(true);
        barChart.setChartData(chartData);
        barChart.setOnClickColumnListener(new OnClickColumnListener<Double>() {
            @Override
            public void onClickColumn(int position, String columnName, Double columnData) {
                Toast.makeText(getBaseContext(), "position:" + position + "name:" + columnName + "columnData:" + columnData, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        setTitle("水位表", getResources().getColor(R.color.title_color));
    }
}
