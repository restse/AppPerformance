package com.midas.performance.aop;


import com.midas.performance.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author midas
 * @date 2019/4/24
 * <p>
 * 面向切面编程,想获取谁的时间就获取谁的时间
 */

@Aspect
public class PerformanceAop {

    /**
     * 获取MyApplication中的方法的时间
     * @param joinPoint
     */
    @Around("call(* com.midas.performance.app.MyApplication.**(..))")
    public void getTime(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LogUtils.i(name + " 耗时cost: " + (System.currentTimeMillis() - time) + "毫秒");
    }

    /**
     * 获取Activity的setContentView的时间
     * @param joinPoint
     */
    @Around("execution(* android.app.Activity.setContentView(..))")
    public void getSetContentViewTime(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LogUtils.i(name + " 耗时cost: " + (System.currentTimeMillis() - time) + "毫秒");
    }


}
