package com.example.xknowledge.ui.event.nested.scrolling

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout

class NestedLayout(context: Context?) : LinearLayout(context) {
    companion object {
        private const val TAG = "NestedLayout"
    }

//    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet)

    //当开启、停止嵌套滚动时调用
    /**
     * 当子View调用startNestedScroll(view,int)后调用该方法，返回true表示响应子View的滚动，实现这个方法声明支持嵌套滑动。
     * 如果返回true，这个视图将要配合子视图嵌套滚动，滚动结束时调用onStopNestedScroll(view)
     * @param child 可滚动的子视图
     * @param target NestedScrollingParent的可直接滚动的视图，一般情况是child
     * @param nestedScrollAxes 包含SCROLL_AXIS_HORIZONTAL，SCROLL_AXIS_VERTICAL，或者两个都有
     * @return 返回true表示响应子视图的滚动
     */
    override fun onStartNestedScroll(child: View?, target: View?, nestedScrollAxes: Int): Boolean {
        Log.i(TAG, "onStartNestedScroll child = $child,target = $target,nestedScrollAxes = $nestedScrollAxes")
        return true
    }

    /**
     * 如果onStartNestedScroll返回true，然后调用该方法做一些初始化
     */
    override fun onNestedScrollAccepted(child: View?, target: View?, axes: Int) {
        Log.i(TAG, "onNestedScrollAccepted child = $child,target = $target,axes = $axes")
    }

    /**
     * 响应嵌套滑结束
     * 当嵌套滑动借宿后（如ACTION_UP，ACTION_CANCEL会调用该方法，可以做一些收尾工作，比如重置变量
     */
    override fun onStopNestedScroll(child: View?) {
        Log.i(TAG, "onStopNestedScroll child = $child")
    }

    //当触摸嵌套滚动时被调用
    /**
     * 子视图滚动前调用该方法，这时候父视图（当前NestedScrollingParent的实现类），可以通过这个方法类配合子视图处理滚动事件
     * @param target 滚动的子视图
     * @param dx 绝对值为手指在x方向滑动的距离，dx<0表示手指在屏幕向右滑动
     * @param dy 绝对值为手指在y方向滑动的距离，dy<0表示手指在屏幕向下滑动
     * @param consumed 一个数组，值用来表示父布局消耗多少距离，未消耗为[0,0]，如果父布局想处理滚动事件，就可以在这个方法实
     * 现中为consumed[0]，consumed[1]赋值，分别表示x和y消耗的距离，如果父视图想在竖直方向完全拦截子视图，让consume[0]=dy
     * 把产生的触摸时间拦截，子视图便响应不到事件了
     */
    override fun onNestedPreScroll(target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        Log.i(TAG, "onNestedPreScroll target = $target,dx = $dx,dy = $dy,consumed[0] = $consumed[0],consumed[1] = $consumed[1]")
    }

    /**
     * 这个方法表示子视图正在滚动，并且把滚动的距离回调到该方法，前提是onStartNestedScroll返回true
     * @param target 滚动的子视图
     * @param dxConsumed 手指产生的触摸距离中，子视图消耗的x方向的距离
     * @param dyConsumed 手指产生的触摸距离中，子视图消耗的y方向的距离，如果onNestedPreScroll中，dy=20，consumed[0]=8，dyConsumed=12
     * @param dxUnconsumed 手指产生的触摸距离中，未被子视图消耗的x方向的距离
     * @param dyUnconsumed 手指产生的触摸距离中，未被子视图消耗的y方向的距离
     */
    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }


    //当惯性嵌套滚动时被调用
    /**
     * 手指在屏幕快速滑动触发Fling前回调，如果前面onNestedPreScroll中父布局消耗了事件，那么这个也会被触发
     * 返回true表示父布局完全处理fling事件
     * @param target 滚动的子视图
     * @param velocityX x方向的速度px/s
     * @param velocityY y方向的速度
     */
    override fun onNestedPreFling(target: View?, velocityX: Float, velocityY: Float): Boolean {
        return super.onNestedPreFling(target, velocityX, velocityY)
    }

    /**
     * 子视图fling时回调，父布局可以选择监听子视图的fling
     * true 表示父布局处理fling，false表示父布局监听子视图的fling
     */
    override fun onNestedFling(target: View?, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return super.onNestedFling(target, velocityX, velocityY, consumed)
    }
}
