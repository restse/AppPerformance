package com.midas.performance.utils;

/**
 * @author Dell
 * @date 2019/4/24
 * <p>
 * 启动时间
 */
public class LaunchTimer {

    private static long sTime;

    public static void startRecord() {
        sTime = System.currentTimeMillis();
    }

    public static void endRecord() {
        endRecord("");
    }

    public static void endRecord(String msg) {
        long cost = System.currentTimeMillis() - sTime;
        LogUtils.i(msg + " 耗时cost: " + cost+"毫秒");
    }

}
