package com.midas.performance.tasks;

import com.midas.performance.launchstarter.task.Task;
import com.umeng.commonsdk.UMConfigure;

/**
 * @author Dell
 * @date : 2019-04-24
 *
 * 友盟统计的初始化
 */
public class InitUmengTask extends Task {

    /**
     * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
     * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
     * UMConfigure.init调用中appkey和channel参数请置为null）。
     *
     * UMConfigure.init(Context context, String appkey, String channel, int deviceType, String pushSecret);
     *
     */
    @Override
    public void run() {
        UMConfigure.init(mContext, "58edcfeb310c93091c000be2", "umeng",
                UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
    }
}
