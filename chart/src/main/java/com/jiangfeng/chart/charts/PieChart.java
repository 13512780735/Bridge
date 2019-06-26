package com.jiangfeng.chart.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.jiangfeng.chart.data.ChartData;
import com.jiangfeng.chart.data.style.FontStyle;
import com.jiangfeng.chart.data.style.LineStyle;
import com.jiangfeng.chart.util.MathUtil;

import java.util.List;

/**
 * Created by LWH
 * 2018/11/30 12:01
 * 饼图
 */
public class PieChart extends PieRadarChartBase<Double> implements IProvider<Double> {
    /**
     * 外切圆范围
     */
    private RectF mRectF;
    /**
     * 开始角度 -90以第一象限Y轴顺时针方向开始;
     */
    private final float mStartAngle = -90;
    /**
     * 中心圆占比
     */
    private float mCenterRadiusRatio = 0.3f;
    /**
     * 同心圆颜色
     */
    private int mCenterCircleColor = Color.WHITE;

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    IProvider<Double> initProvider() {
        return this;
    }


    @Override
    public void drawProvider(Canvas canvas, Paint paint, Rect chartRect, ChartData<Double> chartData) {
        List<Double> columnDataList = chartData.getColumnDataList();
        //最大半径
        int maxRadius = Math.min(chartRect.width() / 2, chartRect.height() / 2);
        //中心圆半径
        double centerRadius = mCenterRadiusRatio * maxRadius;
        int centerX = chartRect.centerX();
        int centerY = chartRect.centerY();
        mRectF = new RectF(chartRect);
        mRectF.left = (float) (centerX - centerRadius);
        mRectF.top = (float) (centerY - centerRadius);
        mRectF.right = (float) (centerX + centerRadius);
        mRectF.bottom = (float) (centerY + centerRadius);
        //扇形内切圆
        RectF sectorRectF = new RectF(centerX - maxRadius, centerY - maxRadius, centerX + maxRadius, centerY + maxRadius);
        LineStyle sectorStyle = new LineStyle();
        sectorStyle.setStyle(Paint.Style.FILL_AND_STROKE);
        FontStyle pointStyle = new FontStyle();
        pointStyle.setTextSize(24f);
        //圆心
        canvas.drawPoint(centerX, centerY, sectorStyle.setWidth(1).fillPaint(paint));
        //扇形圆的范围
        canvas.drawCircle(centerX, centerY, maxRadius, sectorStyle.setColor(Color.BLUE).setStyle(Paint.Style.STROKE).fillPaint(paint));
        //中心内切圆矩形
        canvas.drawRect(mRectF, sectorStyle.setColor(Color.RED).setStyle(Paint.Style.STROKE).fillPaint(paint));
        //图表矩形
        canvas.drawRect(chartRect, sectorStyle.setColor(Color.CYAN).setWidth(1).setStyle(Paint.Style.STROKE).fillPaint(paint));
        //绘制饼图圆弧
        double totalData = 0;
        for (Double data : columnDataList) {
            totalData += data;
        }
        float sweepAngleTotal = 0;
        //圆弧起始点在圆上的坐标
        for (int index = 0; index < columnDataList.size(); index++) {
            //扫过圆弧的角度
            double data = columnDataList.get(index);
            float sweepAngle = (float) (360 * columnDataList.get(index) / totalData);
            int color = chartData.getColors()[index % chartData.getColors().length];
            //绘制扇形
            sectorStyle.setColor(Color.parseColor("#cdf1ed"));
            canvas.drawArc(sectorRectF, mStartAngle + sweepAngleTotal, sweepAngle, true, sectorStyle.setWidth(1).setStyle(Paint.Style.FILL).setColor(color).fillPaint(paint));
            //保存此时画布状态
//            canvas.save();
            //弧线中心点
            PointF pointF = MathUtil.getPointMidOfArcOnCircle(centerX, centerY, maxRadius, sweepAngleTotal, sweepAngle);
            //指示线圆内线
            canvas.drawLine(0.5f * (centerX + pointF.x), 0.5f * (centerY + pointF.y), pointF.x, pointF.y, sectorStyle.setColor(Color.WHITE).setWidth(1).fillPaint(paint));
            canvas.drawPoint(pointF.x, pointF.y, sectorStyle.setColor(color).setWidth(10).fillPaint(paint));
            //指示线圆外线
            if (pointF.x > centerX) {
                canvas.drawLine(pointF.x, pointF.y, pointF.x + 150, pointF.y, sectorStyle.setWidth(1).fillPaint(paint));
                canvas.drawText(String.valueOf(data), pointF.x + 50, pointF.y, pointStyle.setTextColor(color).fillPaint(paint));
            } else {
                canvas.drawLine(pointF.x, pointF.y, pointF.x - 150, pointF.y, sectorStyle.setWidth(1).fillPaint(paint));
                float textWidth = pointStyle.fillPaint(paint).measureText(pointF.x + "," + pointF.y);
                canvas.drawText(String.valueOf(data), pointF.x - 50 - textWidth, pointF.y, pointStyle.setTextColor(color).fillPaint(paint));
            }
            //恢复状态
//            canvas.restore();
            sweepAngleTotal += sweepAngle;
        }
        //同心圆
        canvas.drawCircle(centerX, centerY, (float) centerRadius, sectorStyle.setWidth(1).setColor(mCenterCircleColor).fillPaint(paint));
    }

    public void setCenterRadiusRatio(float centerRadiusRatio) {
        this.mCenterRadiusRatio = centerRadiusRatio;
    }

    public void setCenterCircleColor(int centerCircleColor) {
        this.mCenterCircleColor = centerCircleColor;
    }
}
