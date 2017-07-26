package com.xgh.recruitcmbs.util;

import java.text.ParseException;

/**
 * Created by CQ on 2017/4/5.
 */
public class DateUtilTest {


    public static class TestSimpleDateFormatThreadSafe extends Thread {

        @Override
        public void run() {
            while (true) {
                int i = 0;
                try {
                    this.join(2000);//暂停当前线程周期2秒
                    System.out.println("........." + ++i);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName() + ":" + DateThreadUtil.parse("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new TestSimpleDateFormatThreadSafe().start();
        }
    }
}
