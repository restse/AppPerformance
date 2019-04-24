package com.midas.performance.tasks;

import com.midas.performance.launchstarter.task.Task;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author Dell
 * @date : 2019-04-24
 * <p>
 * Logger的初始化,在子线程
 */
public class InitLoggerTask extends Task {

    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public void run() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
