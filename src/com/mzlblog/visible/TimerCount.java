package com.mzlblog.visible;

import java.util.Date;

public class TimerCount {

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
         long ns = 1000;
         long nsn = 10;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
         long sec = diff % nd % nh % nm / ns;
         //毫秒
        long nss = diff % nd % nh % nm % ns / nsn;
        String nsss =  String.valueOf(nss);
        if(nss <10){
            nsss =  "0"+String.valueOf(nss);
        }
//        return day + "天" + hour + "小时" + min + "分钟"+sec+"秒"+nss+"毫秒";
        return timeFormat(day) + "天 " + timeFormat(hour) + ":" + timeFormat(min) + ":"+timeFormat(sec)+":"+nsss+"";
    }
    public static String getDatePoorNotFormate(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        long nsn = 10;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        //毫秒
        long nss = diff % nd % nh % nm % ns / nsn;
        String nsss =  String.valueOf(nss);
        if(nss <10){
            nsss =  "0"+String.valueOf(nss);
        }
        return day + "天" + hour + "小时" + min + "分钟"+sec+"秒";
    }

    private static String timeFormat(long time){
        return time<10?"0"+time:String.valueOf(time);
    }
}
