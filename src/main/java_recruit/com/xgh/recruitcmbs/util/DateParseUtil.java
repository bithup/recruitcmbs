package com.xgh.recruitcmbs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CQ on 2017/5/9.
 */
public class DateParseUtil {


    public static void main(String[] args) {
        String date_="2017-04-13 09:09:12";

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date2=simpleDateFormat.parse(date_);
            System.out.println(date2);
            String _projectDate = simpleDateFormat.format(date2);
            System.out.println(_projectDate);
            Date date1=java.sql.Date.valueOf(_projectDate);
            System.out.println(date1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
