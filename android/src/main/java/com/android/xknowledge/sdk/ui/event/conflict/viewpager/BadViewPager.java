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
