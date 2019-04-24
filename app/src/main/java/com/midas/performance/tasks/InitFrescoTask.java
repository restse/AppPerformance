package com.midas.performance.tasks;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.midas.performance.launchstarter.task.Task;
import com.midas.performance.net.FrescoTraceListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author midas
 * @date : 2019-04-24
 *
 * Fresco的初始化,在子线程中
 */
public class InitFrescoTask extends Task {

    @Override
    public void run() {
        Set<RequestListener> listenerset = new HashSet<>();
        listenerset.add(new FrescoTraceListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(mContext).setRequestListeners(listenerset)
                .build();
        Fresco.initialize(mContext, config);

    }
}
