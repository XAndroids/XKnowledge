package com.android.xknowledge.ndk.mmap;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;
import com.android.xknowledge.ndk.NativeLib;

public class MmapActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmap);

        //FIXME 为什么报错？？？？
        NativeLib nativeLib = new NativeLib();
        //2019-12-18 14:17:46.042 15428-15428/com.android.xknowledge A/libc: Fatal signal 4 (SIGILL), code 1 (ILL_ILLOPC), fault addr 0xc8fd4720 in tid 15428 (roid.xknowledge), pid 15428 (roid.xknowledge)
        //2019-12-18 14:17:46.120 15580-15580/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //2019-12-18 14:17:46.120 15580-15580/? A/DEBUG: Build fingerprint: 'google/sailfish/sailfish:10/QP1A.191005.007.A3/5972272:user/release-keys'
        //2019-12-18 14:17:46.120 15580-15580/? A/DEBUG: Revision: '0'
        //2019-12-18 14:17:46.120 15580-15580/? A/DEBUG: ABI: 'arm'
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG: Timestamp: 2019-12-18 14:17:46+0800
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG: pid: 15428, tid: 15428, name: roid.xknowledge  >>> com.android.xknowledge <<<
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG: uid: 10244
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG: signal 4 (SIGILL), code 1 (ILL_ILLOPC), fault addr 0xc8fd4720 (*pc=0x9010defe)
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG:     r0  f4a3f000  r1  f4a3f000  r2  00000000  r3  0000ae8d
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG:     r4  df7d3ba0  r5  00000001  r6  00000002  r7  ffb8ac58
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG:     r8  00000000  r9  f5f7be00  r10 ffb8ac80  r11 f5f7be00
        //2019-12-18 14:17:46.121 15580-15580/? A/DEBUG:     ip  849ae7a5  sp  ffb8abd8  lr  c8fd4719  pc  c8fd4720
        //2019-12-18 14:17:46.412 15580-15580/? A/DEBUG: backtrace:
        //2019-12-18 14:17:46.412 15580-15580/? A/DEBUG:       #00 pc 000ca720  /data/app/com.android.xknowledge-PST4uKkc8Pt6EuXhweoScg==/base.apk!libnative-lib.so (offset 0x220e000) (Java_com_android_xknowledge_ndk_NativeLib_writeTest+272) (BuildId: 18715ad67b555bf6003073364408ae292fd8b03d)
        findViewById(R.id.mmap_button_write).setOnClickListener(v -> nativeLib.writeTest());

        //2019-12-18 14:18:16.634 15593-15593/com.android.xknowledge E/native: native crash:/storage/emulated/0/Android/data/com.android.xknowledge/cache/native_crash/72df7baf-d5d7-4627-d1b421bc-6bab7d89.dmp
        //
        //    --------- beginning of crash
        //2019-12-18 14:18:16.635 15593-15593/com.android.xknowledge A/libc: Fatal signal 11 (SIGSEGV), code 1 (SEGV_MAPERR), fault addr 0x0 in tid 15593 (roid.xknowledge), pid 15593 (roid.xknowledge)
        //2019-12-18 14:18:16.707 15721-15721/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //2019-12-18 14:18:16.707 15721-15721/? A/DEBUG: Build fingerprint: 'google/sailfish/sailfish:10/QP1A.191005.007.A3/5972272:user/release-keys'
        //2019-12-18 14:18:16.707 15721-15721/? A/DEBUG: Revision: '0'
        //2019-12-18 14:18:16.707 15721-15721/? A/DEBUG: ABI: 'arm'
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG: Timestamp: 2019-12-18 14:18:16+0800
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG: pid: 15593, tid: 15593, name: roid.xknowledge  >>> com.android.xknowledge <<<
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG: uid: 10244
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG: signal 11 (SIGSEGV), code 1 (SEGV_MAPERR), fault addr 0x0
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG: Cause: null pointer dereference
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG:     r0  ef2ef4a0  r1  00000000  r2  00000064  r3  ef2ef4a0
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG:     r4  df7d3b68  r5  00000001  r6  00000002  r7  ffb8ac58
        //2019-12-18 14:18:16.708 15721-15721/? A/DEBUG:     r8  00000000  r9  f5f7be00  r10 ffb8ac80  r11 f5f7be00
        //2019-12-18 14:18:16.709 15721-15721/? A/DEBUG:     ip  00000003  sp  ffb8ac14  lr  c8fd37fb  pc  f5706494
        //2019-12-18 14:18:16.997 15721-15721/? A/DEBUG: backtrace:
        //2019-12-18 14:18:16.997 15721-15721/? A/DEBUG:       #00 pc 0005d494  /apex/com.android.runtime/lib/bionic/libc.so (__memcpy_kryo+172) (BuildId: 68c87e04526a60689ecb5deb329804a0)
        //2019-12-18 14:18:16.997 15721-15721/? A/DEBUG:       #01 pc 000ca7f7  /data/app/com.android.xknowledge-PST4uKkc8Pt6EuXhweoScg==/base.apk!libnative-lib.so (offset 0x220e000) (Java_com_android_xknowledge_ndk_NativeLib_readTest+82) (BuildId: 18715ad67b555bf6003073364408ae292fd8b03d)
        findViewById(R.id.mmap_button_read).setOnClickListener(v -> nativeLib.readTest());

    }
}