package com.midas.performance.tasks;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.midas.performance.app.MyApplication;
import com.midas.performance.launchstarter.task.Task;

/**
 * @author midas
 * @date : 2019-04-24
 *
 * 获取DeviceId
 */
public class GetDeviceIdTask extends Task {

    @Override
    public void run() {
        // 真正自己的代码
        TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(
                Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String mDeviceId = tManager.getDeviceId();
        MyApplication app = (MyApplication) mContext;
        app.setDeviceId(mDeviceId);
    }
}
