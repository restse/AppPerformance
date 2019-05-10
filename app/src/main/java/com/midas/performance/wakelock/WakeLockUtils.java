package com.midas.performance.wakelock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;

/**
 * @author midas
 * <p>
 * 唤醒锁
 */
public class WakeLockUtils {

    private static PowerManager.WakeLock sWakeLock;

    public static void acquire(Context context) {
        if (sWakeLock == null) {
            sWakeLock = createWakeLock(context);
        }
        if (sWakeLock != null && !sWakeLock.isHeld()) {
            //acquire()方法来保持唤醒
            //sWakeLock.acquire();
            sWakeLock.acquire(1000);
        }
    }

    public static void release() {
        // 一些逻辑
        try {

        } catch (Exception e) {


        } finally {
            // 为了演示正确的使用方式
            if (sWakeLock != null && sWakeLock.isHeld()) {
                //release()方法来释放掉该锁
                sWakeLock.release();
                sWakeLock = null;
            }
        }
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
