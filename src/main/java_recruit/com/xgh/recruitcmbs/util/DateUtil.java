package com.xgh.recruitcmbs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class DateUtil {


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static int dateFromToAge(String start) {

        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String stop = sim.format(new java.util.Date());
        int zs = (Integer.parseInt(stop.substring(0, 4)) - Integer.parseInt(start.substring(0, 4))) - 1;
        int starty = Integer.parseInt(start.substring(5, 7));
        int stopy = Integer.parseInt(stop.substring(5, 7));
        int startz = Integer.parseInt(start.substring(8, 10));
        int stopz = Integer.parseInt(stop.substring(8, 10));
        int yf = 0;
        int jz = 0;
        if (starty > stopy) {
            yf = 12 - starty + stopy;
        } else {
            yf = 12 - stopy + starty;
        }
        if (startz > stopz) {
            jz = (startz - stopz) % 7;
        } else {
            jz = (stopz - startz) % 7;
        }
        System.out.println(zs + "岁" + yf + "个月" + jz + "周");

        return zs;
    }


    /**
     * 根据生日算年龄
     *
     * @param dateOfBirth
     * @return
     */
    public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException(
                        "Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    /**
     * 传递date类型的时间格式化
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 传递string类型的时间格式化
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }

    public static String ConvertUtil(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }


}
