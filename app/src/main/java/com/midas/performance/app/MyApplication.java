package com.midas.performance.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.midas.performance.launchstarter.TaskDispatcher;
import com.midas.performance.memory.ImageHook;
import com.midas.performance.tasks.GetDeviceIdTask;
import com.midas.performance.tasks.InitAMapTask;
import com.midas.performance.tasks.InitBuglyTask;
import com.midas.performance.tasks.InitFrescoTask;
import com.midas.performance.tasks.InitJPushTask;
import com.midas.performance.tasks.InitLoggerTask;
import com.midas.performance.tasks.InitStethoTask;
import com.midas.performance.tasks.InitUmengTask;
import com.midas.performance.tasks.InitWeexTask;
import com.midas.performance.utils.LaunchTimer;
import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;

/**
 * @author midas
 */
public class MyApplication extends Application {

    private static Application mApplication;
    private String mDeviceId;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        LaunchTimer.startRecord();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initTasks();
        LaunchTimer.endRecord();
        hookImg();
    }

    /**
     * initTask
     */
    private void initTasks() {
        TaskDispatcher.init(MyApplication.this);
        TaskDispatcher dispatcher = TaskDispatcher.createInstance();
        dispatcher.addTask(new InitAMapTask())
                .addTask(new InitBuglyTask())
                .addTask(new InitFrescoTask())
                .addTask(new InitJPushTask())
                .addTask(new InitUmengTask())
                .addTask(new InitWeexTask())
                .addTask(new InitStethoTask())
                .addTask(new GetDeviceIdTask())
                .addTask(new InitLoggerTask())
                .start();
        dispatcher.await();
    }

    /**
     * ImageHook
     */
    private void hookImg() {
        DexposedBridge.hookAllConstructors(ImageView.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                DexposedBridge.findAndHookMethod(ImageView.class, "setImageBitmap", Bitmap.class, new ImageHook());
            }
        });

    }


    public static Application getApplication() {
        return mApplication;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setDeviceId(String mDeviceId) {
        this.mDeviceId = mDeviceId;
    }
}
