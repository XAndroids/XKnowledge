package com.android.xknowledge.ndk.base

import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.android.xknowledge.ndk.NativeLib
import kotlinx.android.synthetic.main.activity_base_ndk.*

/**
 * Ndk基本的环境搭建和调用
 */
class BaseNdkActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_ndk)
        //调用原生库得到字符串
        ndk_textview_base.text = NativeLib().stringFromJNI();
        Log.i(
            "BaseNdkActivity", "ndk success:" + NativeLib()
                .stringFromJNI()
        )

        //写 使用 mmap 映射文件 在内存当中 并操作这块内存 往这块内存中写入一块数据
        //错误：已经在设置页面授权了，还是报错。todo... ... 后续进一步研究问题
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: Build fingerprint: 'google/sdk_gphone_x86/generic_x86:10/QSR1.200403.001/6362371:userdebug/dev-keys'
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: Revision: '0'
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: ABI: 'x86'
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: Timestamp: 2020-09-14 17:34:26+0800
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: pid: 1133, tid: 1133, name: roid.xknowledge  >>> com.android.xknowledge <<<
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: uid: 10137
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: signal 11 (SIGSEGV), code 1 (SEGV_MAPERR), fault addr 0x7
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG: Cause: null pointer dereference
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG:     eax dde25390  ebx 64736164  ecx 0000000c  edx ffffffff
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG:     edi ff930020  esi 73616461
        //2020-09-14 17:34:26.039 1231-1231/? A/DEBUG:     ebp ff9300c8  esp ff930014  eip f41cdd2d
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG: backtrace:
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG:       #00 pc 0008dd2d  /apex/com.android.runtime/lib/bionic/libc.so (memmove_generic+1149) (BuildId: 471745f0fbbcedb3db1553d5bd6fcd8b)
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG:       #01 pc 00008852  /data/app/com.android.xknowledge-uxBDCfP9rVCn4dH2Jet8iw==/base.apk!libnative-lib.so (offset 0xc000) (Java_com_android_xknowledge_ndk_NativeLib_writeTest+514) (BuildId: a2916f68e605cfa4349c84963d7a8db68e70dea6)
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG:       #02 pc 00144f67  /apex/com.android.runtime/lib/libart.so (art_quick_generic_jni_trampoline+71) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG:       #03 pc 0013e7d2  /apex/com.android.runtime/lib/libart.so (art_quick_invoke_stub+338) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.314 1231-1231/? A/DEBUG:       #04 pc 00149a69  /apex/com.android.runtime/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+281) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #05 pc 00332502  /apex/com.android.runtime/lib/libart.so (art::interpreter::ArtInterpreterToCompiledCodeBridge(art::Thread*, art::ArtMethod*, art::ShadowFrame*, unsigned short, art::JValue*)+386) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #06 pc 0032c19c  /apex/com.android.runtime/lib/libart.so (bool art::interpreter::DoCall<false, false>(art::ArtMethod*, art::Thread*, art::ShadowFrame&, art::Instruction const*, unsigned short, art::JValue*)+988) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #07 pc 006818dd  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+989) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #08 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #09 pc 00306232  [anon:dalvik-classes.dex extracted in memory from /data/app/com.android.xknowledge-uxBDCfP9rVCn4dH2Jet8iw==/base.apk] (com.android.xknowledge.ndk.base.BaseNdkActivity.onCreate+90)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #10 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #11 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #12 pc 0019175e  /system/framework/framework.jar (android.app.Activity.performCreate+30)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #13 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #14 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #15 pc 00191726  /system/framework/framework.jar (android.app.Activity.performCreate+2)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #16 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #17 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #18 pc 001eefda  /system/framework/framework.jar (android.app.Instrumentation.callActivityOnCreate+6)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #19 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #20 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #21 pc 00180bb8  /system/framework/framework.jar (android.app.ActivityThread.performLaunchActivity+752)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #22 pc 0068461c  /apex/com.android.runtime/lib/libart.so (MterpInvokeDirect+1324) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #23 pc 00138921  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_direct+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #24 pc 00180836  /system/framework/framework.jar (android.app.ActivityThread.handleLaunchActivity+94)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #25 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #26 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #27 pc 0025e28e  /system/framework/framework.jar (android.app.servertransaction.LaunchActivityItem.execute+126)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #28 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #29 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #30 pc 002607da  /system/framework/framework.jar (android.app.servertransaction.TransactionExecutor.executeCallbacks+154)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #31 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #32 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #33 pc 00260716  /system/framework/framework.jar (android.app.servertransaction.TransactionExecutor.execute+146)
        //2020-09-14 17:34:26.315 1231-1231/? A/DEBUG:       #34 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #35 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #36 pc 0017fb6e  /system/framework/framework.jar (android.app.ActivityThread$H.handleMessage+78)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #37 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #38 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #39 pc 002f5846  /system/framework/framework.jar (android.os.Handler.dispatchMessage+38)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #40 pc 00681b4c  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+1612) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #41 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #42 pc 00319ee6  /system/framework/framework.jar (android.os.Looper.loop+466)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #43 pc 00684fdc  /apex/com.android.runtime/lib/libart.so (MterpInvokeStatic+1260) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #44 pc 001389a1  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_static+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #45 pc 00189466  /system/framework/framework.jar (android.app.ActivityThread.main+194)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #46 pc 002f8e0a  /apex/com.android.runtime/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadERKNS_20CodeItemDataAccessorERNS_11ShadowFrameENS_6JValueEbb.llvm.1175793267244191248+298) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #47 pc 002ffcc5  /apex/com.android.runtime/lib/libart.so (art::interpreter::EnterInterpreterFromEntryPoint(art::Thread*, art::CodeItemDataAccessor const&, art::ShadowFrame*)+181) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #48 pc 0066fc49  /apex/com.android.runtime/lib/libart.so (artQuickToInterpreterBridge+1209) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #49 pc 0014503d  /apex/com.android.runtime/lib/libart.so (art_quick_to_interpreter_bridge+77) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #50 pc 0013e9a2  /apex/com.android.runtime/lib/libart.so (art_quick_invoke_static_stub+418) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #51 pc 00149a7a  /apex/com.android.runtime/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+298) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #52 pc 0055a563  /apex/com.android.runtime/lib/libart.so (art::(anonymous namespace)::InvokeWithArgArray(art::ScopedObjectAccessAlreadyRunnable const&, art::ArtMethod*, art::(anonymous namespace)::ArgArray*, art::JValue*, char const*)+99) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #53 pc 0055c37f  /apex/com.android.runtime/lib/libart.so (art::InvokeMethod(art::ScopedObjectAccessAlreadyRunnable const&, _jobject*, _jobject*, _jobject*, unsigned int)+1327) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #54 pc 004c91a3  /apex/com.android.runtime/lib/libart.so (art::Method_invoke(_JNIEnv*, _jobject*, _jobject*, _jobjectArray*)+83) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #55 pc 000c6bf8  /system/framework/x86/boot.oat (art_jni_trampoline+168) (BuildId: 7913dbaef2e8d9971cb7619ef0d566987f8326a7)
        //2020-09-14 17:34:26.316 1231-1231/? A/DEBUG:       #56 pc 0013e7d2  /apex/com.android.runtime/lib/libart.so (art_quick_invoke_stub+338) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #57 pc 00149a69  /apex/com.android.runtime/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+281) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #58 pc 00332502  /apex/com.android.runtime/lib/libart.so (art::interpreter::ArtInterpreterToCompiledCodeBridge(art::Thread*, art::ArtMethod*, art::ShadowFrame*, unsigned short, art::JValue*)+386) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #59 pc 0032c19c  /apex/com.android.runtime/lib/libart.so (bool art::interpreter::DoCall<false, false>(art::ArtMethod*, art::Thread*, art::ShadowFrame&, art::Instruction const*, unsigned short, art::JValue*)+988) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #60 pc 006818dd  /apex/com.android.runtime/lib/libart.so (MterpInvokeVirtual+989) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #61 pc 00138821  /apex/com.android.runtime/lib/libart.so (mterp_op_invoke_virtual+33) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #62 pc 0034ccb2  /system/framework/framework.jar (com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run+22)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #63 pc 002f8e0a  /apex/com.android.runtime/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadERKNS_20CodeItemDataAccessorERNS_11ShadowFrameENS_6JValueEbb.llvm.1175793267244191248+298) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #64 pc 002ffcc5  /apex/com.android.runtime/lib/libart.so (art::interpreter::EnterInterpreterFromEntryPoint(art::Thread*, art::CodeItemDataAccessor const&, art::ShadowFrame*)+181) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #65 pc 0066fc49  /apex/com.android.runtime/lib/libart.so (artQuickToInterpreterBridge+1209) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #66 pc 0014503d  /apex/com.android.runtime/lib/libart.so (art_quick_to_interpreter_bridge+77) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #67 pc 00998b58  /system/framework/x86/boot-framework.oat (com.android.internal.os.ZygoteInit.main+1816) (BuildId: 59c2231d89881ed760171c2061ef3d491a1198a7)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #68 pc 0013e9a2  /apex/com.android.runtime/lib/libart.so (art_quick_invoke_static_stub+418) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #69 pc 00149a7a  /apex/com.android.runtime/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+298) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #70 pc 0055a563  /apex/com.android.runtime/lib/libart.so (art::(anonymous namespace)::InvokeWithArgArray(art::ScopedObjectAccessAlreadyRunnable const&, art::ArtMethod*, art::(anonymous namespace)::ArgArray*, art::JValue*, char const*)+99) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #71 pc 0055a1fe  /apex/com.android.runtime/lib/libart.so (art::InvokeWithVarArgs(art::ScopedObjectAccessAlreadyRunnable const&, _jobject*, _jmethodID*, char*)+430) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #72 pc 004305cd  /apex/com.android.runtime/lib/libart.so (art::JNI::CallStaticVoidMethodV(_JNIEnv*, _jclass*, _jmethodID*, char*)+893) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #73 pc 003d93bf  /apex/com.android.runtime/lib/libart.so (art::(anonymous namespace)::CheckJNI::CallMethodV(char const*, _JNIEnv*, _jobject*, _jclass*, _jmethodID*, char*, art::Primitive::Type, art::InvokeType)+2847) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #74 pc 003c7509  /apex/com.android.runtime/lib/libart.so (art::(anonymous namespace)::CheckJNI::CallStaticVoidMethodV(_JNIEnv*, _jclass*, _jmethodID*, char*)+73) (BuildId: fe49ff2b6f401678e4775fb2121e4ea4)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #75 pc 000b25fe  /system/lib/libandroid_runtime.so (_JNIEnv::CallStaticVoidMethod(_jclass*, _jmethodID*, ...)+62) (BuildId: 3643bee2c4fb7899d7781c565843060b)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #76 pc 000b628a  /system/lib/libandroid_runtime.so (android::AndroidRuntime::start(char const*, android::Vector<android::String8> const&, bool)+794) (BuildId: 3643bee2c4fb7899d7781c565843060b)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #77 pc 00003632  /system/bin/app_process32 (main+1490) (BuildId: b7a60bc7d078521421fd5a8d201915ae)
        //2020-09-14 17:34:26.317 1231-1231/? A/DEBUG:       #78 pc 000898e8  /apex/com.android.runtime/lib/bionic/libc.so (__libc_init+120) (BuildId: 471745f0fbbcedb3db1553d5bd6fcd8b)
        //2020-09-14 17:34:26.509 1921-1921/? E//system/bin/tombstoned: Tombstone written to: /data/tombstones/tombstone_11
//        NativeLib().writeTest()
//        NativeLib().readTest()
    }
}
