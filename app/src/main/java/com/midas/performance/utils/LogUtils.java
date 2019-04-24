package com.midas.performance.utils;

import com.midas.performance.app.MyApplication;
import com.orhanobut.logger.Logger;

import java.util.concurrent.ExecutorService;

/**
 *
 *@author Dell
 *@date  2019/4/24
 *
 */
public class LogUtils {

    private static ExecutorService sExecutorService;

    public static void setExecutor(ExecutorService executorService){
        sExecutorService = executorService;
    }

    private static final String TAG = "performance";

    public static void i(String msg){
        if(Utils.isMainProcess(MyApplication.getApplication())){
            Logger.i(msg);
        }
        // 异步
        if(sExecutorService != null){
//            sExecutorService.execute();
        }
    }

}
