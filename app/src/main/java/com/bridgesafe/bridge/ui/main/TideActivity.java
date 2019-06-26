package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.util.StringUtil;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.jiangfeng.chart.charts.BarChart;
import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.listener.OnClickColumnListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 潮汐表
 */
public class TideActivity extends BaseActivity {
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    private String time;
    @BindView(R.id.main_barChart)
    BarChart mBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tide);
        setBackView();
        initUI();
        initBarChart(mBarChart);
    }

    private void initBarChart(BarChart barChart) {
        int size = 6;
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
        barChart.setXScaleSize(6);
        barChart.setYScaleSize(7);
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
        setTitle("天气", getResources().getColor(R.color.title_color));
        mTvTime.setText(StringUtil.getCurrentDate());


    }

    @OnClick({R.id.tv_address, R.id.tv_time})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_address:
                break;
            case R.id.tv_time:
                showDatePickDialog(DateType.TYPE_YMD);
                break;
        }
    }

    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(100);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                mTvTime.setText(StringUtil.getDate(date, "yyyy-MM-dd"));
                time = String.valueOf((StringUtil.getStringToDate(StringUtil.getDate(date, "yyyy-MM-dd"), "yyyy-MM-dd")) / 1000);
                // XLog.e("time-->"+time);
            }
        });
        dialog.show();
    }
}
