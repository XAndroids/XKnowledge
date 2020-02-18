package com.android.xknowledge.jetpack.navigation

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.android.xknowledge.R

/**
 * DeepLink AppWidget，参考：https://developer.android.com/guide/topics/appwidgets/index.html#AppWidgetProvider
 */
class DeepLinkAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.appwidget_deeplink)
        //通过setArguments(args)向目标deepLinkFragment传递Bundle参数
        val args = Bundle()
        args.putString("myarg", "From Widget")

        //由于XKnowledge项目是多Activity，不通过setComponentName(NavigationActivity::class.java)的话，会默认
        //启动MainActivity
        //DeepLink的返回堆栈，使用你传入的导航图，使用app:startDestination指定的目标生成，所以按返回键会返回到HomeFragment
        val pendingIntent =
            NavDeepLinkBuilder(context).setComponentName(NavigationActivity::class.java)
                .setGraph(R.navigation.navigation_graph)
                .setDestination(R.id.deepLinkFragment).setArguments(args).createPendingIntent()
        remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent)

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }
}