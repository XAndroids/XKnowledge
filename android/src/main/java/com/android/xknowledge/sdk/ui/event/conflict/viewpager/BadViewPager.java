package com.android.xknowledge.sdk.ui.event.conflict.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * ViewPager系统默认已经处理了和List的事件冲突
 * 此处是覆盖onInterceptTouchEvent，不使用系统默认的事件冲突处理，自己处理事件冲突
 */
public class BadViewPager extends ViewPager {

    private int mLastX, mLastY;

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
    //拦截事件，不会传递给ListView处理，左右可以，上下不可以
//        return true;
    //不拦截事件，会传递给ListView处理，上下可滑动，左右不可以
//        return false;
//    }

    // 外部拦截法：父容器处理冲突
    // 我想要把事件分发给谁就分发给谁
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //内部拦截法1：
        //在ACTION_DOWN时，不拦截事件，让ListView处理事件形成拦截链，否则一旦ViewPager拦截后ListView无法再重
        //新获取事件处理权；
        //在ACTION_MOVE时，拦截事件，让ListView在横向滑动时，通过requestDisallowInterceptTouchEvent(false)
        //将事件处理权重新交给ViewPager，实现ViewPager横向滑动；
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            super.onInterceptTouchEvent(event);
//            return false;
//        }
//        return true;

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            }
            //为横向滑动时，onInterceptTouchEvent()返回true，拦截事件，自己处理实现ViewPager横向滑动；
            //为竖向滑动时，onInterceptTouchEVent()返回fase，不拦截事件，分发给子View ListView处理，
            //实现ListView竖向滑动；
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        return super.onInterceptTouchEvent(event);
    }
}
