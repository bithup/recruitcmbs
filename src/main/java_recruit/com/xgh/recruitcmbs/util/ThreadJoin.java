package com.xgh.recruitcmbs.util;



/**
 * Created by CQ on 2017/4/5.
 */
public class ThreadJoin {


    public static int a = 100;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 5; i++)
                    a++;
            }
        });
        thread.start();
        try {
            thread.join(10000);//为0代表等线程执行完毕再执行sysout方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);

    }


}
