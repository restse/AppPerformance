package com.midas.performance.tasks;


import com.midas.performance.app.MyApplication;
import com.midas.performance.launchstarter.task.Task;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * @author midas
 * Date: 2019-04-24
 * <p>
 * 极光推送的初始化 需要在getDeviceId之后执行
 */
public class InitJPushTask extends Task {

    @Override
    public List<Class<? extends Task>> dependsOn() {
        List<Class<? extends Task>> task = new ArrayList<>();
        task.add(GetDeviceIdTask.class);
        return task;
    }

    @Override
    public void run() {
        JPushInterface.init(mContext);
        MyApplication app = (MyApplication) mContext;
        JPushInterface.setAlias(mContext, 0, app.getDeviceId());
    }
}
