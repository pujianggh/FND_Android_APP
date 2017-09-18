package com.fengniao.action.commonlibrary.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具
 *
 * @author pujiang
 * @date 2017-8-24 17:00
 * @mail 515210530@qq.com
 * @Description:
 */
public class DateToolsUtils {

    /**
     * 获取到不同类型的时间
     *
     * @param timeStr
     * @param type
     * @return
     */
    public static String getFormatTime(String timeStr, int type) {
        String str = "";
        try {
            if (timeStr != null && !timeStr.equals("")) {
                long time = Long.parseLong(timeStr);
                str = DateFormat.format("yyyy-MM-dd kk:mm", time).toString();
                if (type == 1) {
                    str = DateFormat.format("yyyy-MM-dd", time).toString();
                } else if (type == 2) {
                    str = DateFormat.format("yyyy-MM-dd kk:mm:ss", time)
                            .toString();
                } else if (type == 3) {
                    str = DateFormat.format("MM月dd日 kk:mm", time)
                            .toString();
                } else if (type == 4) {
                    str = DateFormat.format("kk:mm", time)
                            .toString();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogAPPUtils.e("getFormatTime->"+e.getStackTrace());
        }
        return str;
    }

    /**
     * 显示时间
     *
     * @param timeStr
     * @return
     */
    public static String getShowTime(String timeStr) {
        String str = "";
        try {
            if (timeStr != null && !"".equals(timeStr)) {
                Calendar oldCal = Calendar.getInstance();
                Calendar newCal = Calendar.getInstance();
                oldCal.setTime(new Date(System.currentTimeMillis()));
                newCal.setTime(new Date(Long.parseLong(timeStr)));
                long time = Long.parseLong(timeStr);
                if (Math.abs(newCal.get(Calendar.DAY_OF_YEAR)
                        - oldCal.get(Calendar.DAY_OF_YEAR)) == 1) {
                    str = DateFormat.format(" kk:mm", time).toString();
                    str = "昨天";
                } else if (Math.abs(newCal.get(Calendar.DAY_OF_YEAR)
                        - oldCal.get(Calendar.DAY_OF_YEAR)) <= 1) {
                    str = DateFormat.format("kk:mm", time).toString();
                    str = "今天";
                } else {
                    str = DateFormat.format("MM-dd", time).toString();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogAPPUtils.e("getShowTime->"+e.getStackTrace());
        }
        return str;
    }

    /**
     * 返回当前的时间
     *
     * @return
     */
    public static String getSimpleDate() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 返回日期的天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}