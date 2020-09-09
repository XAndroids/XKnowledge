package com.android.xknowledge.framework.aspectj.login;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CheckTestAspectJ {

    @Pointcut("execution(* *.onCreate(..))")
    public void injectSpendCode() {
    }

    @Around("injectSpendCode()")
    public void aroundOnCreate(final ProceedingJoinPoint joinPoint) throws Throwable {
        //插入的代码
        long start = System.currentTimeMillis();
        //执行原来的代码
        Object[] params = joinPoint.getArgs();
        joinPoint.proceed(params);
        //插入的代码
        Log.i("SpendTimeAspect", "method spend " + (System.currentTimeMillis() - start));
    }
}
