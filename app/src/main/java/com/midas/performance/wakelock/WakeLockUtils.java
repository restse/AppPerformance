package com.midas.performance.wakelock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import com.midas.performance.utils.LogUtils;

/**
 * @author midas
 * <p>
 * 唤醒锁
 */
public class WakeLockUtils {

    private static PowerManager.WakeLock sWakeLock;

    /**ActivityHooker 不打log 暂时写在类里面*/
    public static String trace;
    public static long sStartTime = 0;

    public static void acquire(Context context) {

        trace = Log.getStackTraceString(new Throwable());
        sStartTime = System.currentTimeMillis();

        if (sWakeLock == null) {
            sWakeLock = createWakeLock(context);
        }
        if (sWakeLock != null && !sWakeLock.isHeld()) {
            //acquire()方法来保持唤醒
            sWakeLock.acquire(); //使用带参 acquire()
            //sWakeLock.acquire(1000);
        }
    }

    public static void release() {
        // 一些逻辑
        try {

        } catch (Exception e) {


        } finally {
            // 为了演示正确的使用方式  确保一定被释放
            if (sWakeLock != null && sWakeLock.isHeld()) {
                //release()方法来释放掉该锁
                sWakeLock.release();
                sWakeLock = null;
            }
        }
        // print ----- PowerManager 204/njava.lang.Throwable
        LogUtils.i("PowerManager " + (System.currentTimeMillis() - sStartTime) + "/n" + trace);
    }

    /**
     * PowerManager 用来控制设备的电源状态.
     * 而PowerManager.WakeLock 也称作唤醒锁, 是一种保持 CPU 运转防止设备休眠的方式
     * <p>
     * 只是需要保持屏幕开启, 比如阅读器应用或者游戏, 可以在 activity 中使用 FLAG_KEEP_SCREEN_ON.
     * 唤醒锁更加倾向于后台服务, 运转 CPU 在休眠之前完成某些特定任务. 比如下载或者mp3播放 即使屏幕关闭后台也能保持运行
     * <p>
     * 尽量只考虑在服务中使用 PARTIAL_WAKE_LOCK, 在 activity 中使用 FLAG_KEEP_SCREEN_ON
     * <p>
     * <uses-permission android:name="android.permission.WAKE_LOCK" />
     * <p>
     * 可能还需要:
     * <uses-permission android:name="android.permission.DEVICE_POWER"/>
     * <p>
     * <p>
     * acquire  release成对出现
     */

    @SuppressLint("InvalidWakeLockTag")
    private static PowerManager.WakeLock createWakeLock(Context context) {
        PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            return pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        }
        return null;
    }

}
