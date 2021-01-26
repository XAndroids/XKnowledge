package com.android.xknowledge.sdk.other
//
//import android.app.ApplicationExitInfo
//import android.os.Build
//
///**
// * 进程退出原因，按照退出时间排序
// */
//class ExitComparator : Comparator<ApplicationExitInfo> {
//    override fun compare(p0: ApplicationExitInfo, p1: ApplicationExitInfo): Int {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            when {
//                p0.timestamp < p1.timestamp -> {
//                    -1
//                }
//                p0.timestamp == p1.timestamp -> {
//                    0
//                }
//                else -> {
//                    1
//                }
//            }
//        } else {
//            0
//        }
//    }
//}