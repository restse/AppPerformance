package com.midas.performance.tasks;

import com.midas.performance.launchstarter.task.Task;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author Dell
 * @date : 2019-04-24
 *
 * Bugly的初始化,在子线程中
 */
public class InitBuglyTask extends Task {

    @Override
    public void run() {
        CrashReport.initCrashReport(mContext, "注册时申请的APPID", false);
    }
}
