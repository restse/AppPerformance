package com.midas.performance.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.midas.performance.launchstarter.TaskDispatcher;
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

    }

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
        LaunchTimer.endRecord();
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
