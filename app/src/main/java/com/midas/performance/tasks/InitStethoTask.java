package com.midas.performance.tasks;

import com.facebook.stetho.Stetho;
import com.midas.performance.launchstarter.task.Task;


/**
 * @author Dell
 * @date : 2019-04-24
 *
 * Stetho的初始化 异步的Task
 */
public class InitStethoTask extends Task {

    @Override
    public void run() {
        // Handler handler = new Handler(Looper.getMainLooper());
        Stetho.initializeWithDefaults(mContext);
    }
}
