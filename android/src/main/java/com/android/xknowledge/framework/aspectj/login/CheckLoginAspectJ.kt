package com.android.xknowledge.framework.aspectj.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.xknowledge.XApplication
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class CheckLoginAspectJ {
    private val TAG = "CheckLogin"

    @Pointcut("com.com.android.xknowledge.framework.aspectj.login.CheckLogin  * *(..))")
    fun executionCheckLogin() {
    }

    @Around("executionCheckLogin()")
    @Throws(Throwable::class)
    fun checkLogin(joinPoint: ProceedingJoinPoint): Any? {
        Log.i(TAG, "checkLogin: ");
        val signature = joinPoint.signature as MethodSignature
        val checkLogin = signature.method.getAnnotation(CheckLogin::class.java)
        if (checkLogin != null) {
            val context = joinPoint.getThis() as Context
            return if (XApplication.isLogin) {
                joinPoint.proceed()
            } else {
                Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show()
                null
            }
        }
        return null
    }
}