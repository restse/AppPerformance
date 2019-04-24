package com.midas.performance.tasks;

import android.app.Application;
import com.midas.performance.launchstarter.task.MainTask;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * @author midas
 * @date : 2019-04-24
 *
 * 主线程执行的task
 */
public class InitWeexTask extends MainTask {

    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public void run() {
        InitConfig config = new InitConfig.Builder().build();
        WXSDKEngine.initialize((Application) mContext, config);
    }
}
