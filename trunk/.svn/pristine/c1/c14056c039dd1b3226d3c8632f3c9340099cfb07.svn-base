package com.taiwan.oomatcher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.R.attr.format;
import static com.taiwan.oomatcher.R.id.date;
import static com.taiwan.oomatcher.R.id.hour;
import static com.taiwan.oomatcher.R.id.min;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/21 16:00
 */

public class DateUtils {

    /**
     * 获得当前日期的星期一
     * @return
     */
    public static int cpmpareDate(Date start, Date end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int startInt = Integer.parseInt(format.format(start));
        int endInt = Integer.parseInt(format.format(end));
        if(startInt > endInt){
            return 1;
        }else if(startInt < endInt){
            return -1;
        }
        return 0;
    }

    /**
     * 获得当前日期的星期一
     * @return
     */
    public static Date getCurrentMonday(Date currentDate) {
        int mondayPlus = getMondayPlus(currentDate);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        calendar.add(GregorianCalendar.DATE, mondayPlus);
        return calendar.getTime();
    }

    /**
     * 获得当前日期与本周一相差的天数
     * @return
     */
    public static int getMondayPlus(Date currentDate) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(currentDate);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得相应周的周日的日期
    public static Date getSunday(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE,6);
        return calendar.getTime();
    }

    private ArrayList<Date> getWeekDataList(Date date){
        Date tempDate = date;
        ArrayList<Date> list = new ArrayList<Date>();
        list.add(date);
        for(int i = 0; i < 6; i++){
            tempDate = getNextDay(tempDate);
            list.add(tempDate);
        }
        return list;
    }

    public static Date getNextDay(Date date){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DATE, 1);
        return startCalendar.getTime();
    }
    public static Date getPreviouDay(Date date){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DATE, -1);
        return startCalendar.getTime();
    }

    public static ArrayList<Date> getNextDayList(Date date, int count){
        Date tempDate = date;
        ArrayList<Date> list = new ArrayList<Date>();
        list.add(date);
        for(int i = 0; i < count; i++){
            tempDate = getNextDay(tempDate);
            list.add(tempDate);
        }
        return list;
    }

    public static ArrayList<Date> getPreviouDayList(Date date, int count){
        Date tempDate = date;
        ArrayList<Date> list = new ArrayList<Date>();
        list.add(0, date);
        for(int i = 0; i < count; i++){
            tempDate = getPreviouDay(tempDate);
            list.add(0, tempDate);
        }
        return list;
    }

    public static long[] getDayHour(Date start, Date end){
        long l=end.getTime() - start.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long[] result = new long[2];
        result[0] = day;
        result[1] = hour;
        return result;
    }

    public static int[] getDayHour(String startStr, String endStr){
        System.out.println(startStr);
        System.out.println(endStr);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] result = null;
        try {
            Date end = df.parse(endStr);
            Date start = df.parse(startStr);
            long l = end.getTime() - start.getTime();
            long day = l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            result = new int[2];
            result[0] = (int) day;
            result[1] = (int) hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getTaskDateStr(Date date, String hour){
        Date d = getTaskDate(date, hour);
        return d.getTime()+"";
    }
    public static Date getTaskDate(Date date, String hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat mYearFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = format.parse(mYearFormat.format(date)+" " + hour+":00");
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
    public static String[] getTaskDate(long time){
        String[] result = new String[4];
        long between = time - System.currentTimeMillis();
        if(between < 0){
            result[0] = "0";
            result[1] = "00";
            result[2] = "00";
            result[3] = "00";
            return result;
        }
        int day = (int) (between/(24*60*60*1000));
        int hour= (int) (between/(60*60*1000)-day*24);
        int min = (int) ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        int s = (int) (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        day = day < 0 ? 0 : day;
        hour = hour < 0 ? 0 : hour;
        min = min < 0 ? 0 : min;
        s = s < 0 ? 0 : s;

        result[0] = day + "";
        result[1] = hour < 10 ? "0" + hour : hour + "";
        result[2] = min < 10 ? "0" + min : min + "";
        result[3] = s < 10 ? "0" + s : s+"";
        return result;
    }

    public static String getTasShowTime(Date date, String day, String hour){
        long l = date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = l + (Integer.parseInt(day)*24*60*60*1000) +  (Integer.parseInt(hour)*60*60*1000);
        Date date_3_hm_date = new Date(time);
        return date_3_hm_date.getTime()+"";
    }

}
