package com.jiangfeng.chart.util;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by LWH
 * 2018/12/1 11:25
 */
public class MathUtil {
    /**
     * 已知圆心坐标，半径，弧度，求圆心过弧度中垂线的直接与圆的交叉点坐标
     * 仅适用于扇形弧度sweepAngle从坐标系Y轴(第一象限)顺时针方向绘制的算法
     * 取的是弧线中垂线与圆的交叉点，角度θ∈[0,360],θ/2 ∈[0,180],所以只区分2次
     *
     * @param centerX    圆心X坐标
     * @param centerY    圆心Y坐标
     * @param radius     圆半径
     * @param sweepAngle 圆弧度
     * @return 交叉点坐标
     */
    public static PointF getPointMidOfArcOnCircleFromZero(float centerX, float centerY, float radius, float sweepAngle) {
        PointF point = new PointF();
        //不同坐标系根据偏移量算圆弧中心点坐标
        if (sweepAngle >= 0 && sweepAngle <= 180) {
            double tan = Math.tan(Math.toRadians(sweepAngle / 2));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX + width;
            point.y = centerY - height;
        } else if (sweepAngle > 180 && sweepAngle <= 360) {
            double tan = Math.tan(Math.toRadians(180 - sweepAngle / 2));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX + width;
            point.y = centerY + height;
            Log.i("MathUtil", "---degree" + sweepAngle + "---tan:" + tan + ",height:" + height);
        }
        return point;
    }

    public static PointF getPointMidVerticalOfArc(float centerX, float centerY, float radius, float startAngle, float sweepAngle) {
        if (sweepAngle < 0 || sweepAngle > 360) {
            throw new IllegalArgumentException("扇形角度sweepAngle必须满足θ∈[0,360]");
        }
        PointF point = new PointF();
        //转过startAngle与圆的交叉点
        PointF pointF1 = getPointEndOfArcOnCircle(centerX, centerY, radius, startAngle);
        //转过startAngle+sweepAngle与圆的交叉点
        PointF pointF2 = getPointEndOfArcOnCircle(centerX, centerY, radius, startAngle + sweepAngle);
        //两点之间的中点
        PointF pointFMid = new PointF((pointF1.x + pointF2.x) / 2, (pointF1.y + pointF2.y) / 2);
        //直线斜率  y=kx  过圆心和中心点pointFMid
        float k = Math.abs((pointFMid.y - centerY) / (pointFMid.x - centerX));
        return pointFMid;
    }

    /**
     * 已知圆心坐标，半径，从第一象限到弧度开始边界的角度,圆弧角度，求圆心过弧度中垂线的直接与圆的交叉点坐标
     * 仅适用于从坐标系Y轴(第一象限)顺时针方向绘制的算法
     *
     * @param centerX    圆心X坐标
     * @param centerY    圆心Y坐标
     * @param radius     圆半径
     * @param startAngle 从第一象限到弧度开始边界的角度
     * @param sweepAngle 圆弧度
     * @return 交叉点坐标
     */
    public static PointF getPointMidOfArcOnCircle(float centerX, float centerY, float radius, float startAngle, float sweepAngle) {
        if (sweepAngle < 0 || sweepAngle > 360) {
            throw new IllegalArgumentException("扇形角度sweepAngle必须满足θ∈[0,360]");
        }
        PointF point = new PointF();
        //转过startAngle与圆的交叉点
        PointF pointF1 = getPointEndOfArcOnCircle(centerX, centerY, radius, startAngle);
        //转过startAngle+sweepAngle与圆的交叉点
        PointF pointF2 = getPointEndOfArcOnCircle(centerX, centerY, radius, startAngle + sweepAngle);
        //两点之间的中点
        PointF pointFMid = new PointF((pointF1.x + pointF2.x) / 2, (pointF1.y + pointF2.y) / 2);
        //直线斜率  y=kx  过圆心和中心点pointFMid
        float k = Math.abs((pointFMid.y - centerY) / (pointFMid.x - centerX));
//        float b = pointFMid.y - k * pointFMid.x;
        //底数
        double radix = 1 + Math.pow(k, 2);
        float width = (float) Math.abs(Math.sqrt(Math.pow(radius, 2) / radix));
        float height = Math.abs(k * width);
        //扇形弧度的一半+开始的角度=扇形的一半落在的象限
        float totalAngleHalf = startAngle + sweepAngle / 2;
        //从第1象限开始;
        if (startAngle >= 0 && startAngle <= 90) {
            //根据扇形的一般弧度落在的象限分别计算;
            if (totalAngleHalf >= 0 && totalAngleHalf <= 90) {
                point.x = centerX + width;
                point.y = centerY - height;
            } else if (totalAngleHalf > 90 && totalAngleHalf <= 180) {
                point.x = centerX + width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 180 && totalAngleHalf <= 270) {
                point.x = centerX - width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 270 && totalAngleHalf <= 360) {
                point.x = centerX - width;
                point.y = centerY - height;
            }
            //从第二象限开始
        } else if (startAngle > 90 && startAngle <= 180) {
            //根据扇形的一般弧度落在的象限分别计算;
            if (totalAngleHalf >= 0 && totalAngleHalf <= 90) {
                point.x = centerX + width;
                point.y = centerY - height;
            } else if (totalAngleHalf > 90 && totalAngleHalf <= 180) {
                point.x = centerX + width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 180 && totalAngleHalf <= 270) {
                point.x = centerX - width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 270 && totalAngleHalf <= 360) {
                point.x = centerX - width;
                point.y = centerY - height;
            }
            //从第三象限开始
        } else if (startAngle > 180 && startAngle <= 270) {
            //根据扇形的一般弧度落在的象限分别计算;
            if (totalAngleHalf >= 0 && totalAngleHalf <= 90) {
                point.x = centerX + width;
                point.y = centerY - height;
            } else if (totalAngleHalf > 90 && totalAngleHalf <= 180) {
                point.x = centerX + width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 180 && totalAngleHalf <= 270) {
                point.x = centerX - width;
                point.y = centerY + height;
            } else if (totalAngleHalf > 270 && totalAngleHalf <= 360) {
                point.x = centerX - width;
                point.y = centerY - height;
            }
            //从第四象限开始
        } else if (startAngle > 270 && startAngle <= 360) {
            if (sweepAngle >= 0 && sweepAngle <= 90) {
                point.x = centerX - width;
                point.y = centerY - height;
            } else {
                throw new IllegalArgumentException("扇形总角度不能大于360");
            }
        }
        Log.i("MathUtil", "---startAngle" + startAngle + "---sweepAngle:" + sweepAngle + "--k:" + k + "--width:" + width + "--height:" + height);
        return point;
    }

    /**
     * 已知圆心坐标，半径，弧度，求圆弧结束点在圆上的坐标
     * 仅适用于从坐标系Y轴(第一象限)顺时针方向绘制的算法
     *
     * @param centerX    圆心X坐标
     * @param centerY    圆心Y坐标
     * @param radius     圆半径
     * @param sweepAngle 圆弧度
     * @return 交叉点坐标
     */
    public static PointF getPointEndOfArcOnCircle(float centerX, float centerY, float radius, float sweepAngle) {
        PointF point = new PointF();
        //不同坐标系根据偏移量算圆弧中心点坐标
        //tan0°＝0,tan90°不存在,tan180°＝0,tan270°不存在,tan360°＝0
        if (sweepAngle == 0) {
            point.x = centerX;
            point.y = centerY - radius;
        } else if (sweepAngle >= 0 && sweepAngle < 90) {
            double tan = Math.tan(Math.toRadians(sweepAngle));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX + width;
            point.y = centerY - height;
        } else if (sweepAngle == 90) {
            point.x = centerX + radius;
            point.y = centerY;
        } else if (sweepAngle > 90 && sweepAngle <= 180) {
            double tan = Math.tan(Math.toRadians(180 - sweepAngle));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX + width;
            point.y = centerY + height;
        } else if (sweepAngle > 180 && sweepAngle < 270) {
            double tan = Math.tan(Math.toRadians(sweepAngle - 180));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX - width;
            point.y = centerY + height;
        } else if (sweepAngle == 270) {
            point.x = centerX - radius;
            point.y = centerY;
        } else if (sweepAngle > 270 && sweepAngle < 360) {
            double tan = Math.tan(Math.toRadians(360 - sweepAngle));
            //底数
            double radix = 1 + Math.pow(tan, 2);
            float height = (float) Math.sqrt(Math.pow(radius, 2) / radix);
            float width = (float) (height * tan);
            point.x = centerX - width;
            point.y = centerY - height;
        } else {
            point.x = centerX;
            point.y = centerY - radius;
        }
        return point;
    }
}
