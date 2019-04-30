package com.midas.performance.tasks;

import com.midas.performance.app.MyApplication;
import com.midas.performance.launchstarter.task.Task;
import com.tencent.smtt.sdk.QbSdk;

/**
 * @author midas
 * @date : 2019-04-30
 * <p>
 * Tbs X5WebView的初始化,在子线程中
 */
public class InitTbsTask extends Task {

    @Override
    public void run() {
        //非wifi情况下，主动下载x5内核
        ///QbSdk.setDownloadWithoutWifi(true);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                /// Log.e("init QbSdk", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
                //  Log.e("init QbSdk", " onViewInitFinished is fail");
            }
        };
        MyApplication app = (MyApplication) mContext;
        //x5内核初始化接口
        QbSdk.initX5Environment(app, cb);
    }
}
