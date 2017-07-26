package com.xgh.recruitcmbs.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by CQ on 2017/5/16.
 */
public class DoubleUtil {


    public static double formatDouble2(double d) {
        // 旧方法，已经不再推荐使用
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);


        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);


        return bg.doubleValue();
    }


}
