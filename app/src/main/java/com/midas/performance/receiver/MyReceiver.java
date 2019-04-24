package com.midas.performance.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.jpush.android.api.JPushInterface;

/**
 * @author midas
 * @date : 2019-04-24
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {

            case JPushInterface.ACTION_REGISTRATION_ID:
                System.out.println("JPush用户注册成功");
                break;

            case JPushInterface.ACTION_MESSAGE_RECEIVED:
                System.out.println("接受到推送下来的自定义消息");
                break;

            case JPushInterface.ACTION_NOTIFICATION_RECEIVED:
                System.out.println("接受到推送下来的通知");
                break;

            case JPushInterface.ACTION_NOTIFICATION_OPENED:
                System.out.println("用户点击打开了通知");
                break;

            default:
                break;

        }
    }
}
