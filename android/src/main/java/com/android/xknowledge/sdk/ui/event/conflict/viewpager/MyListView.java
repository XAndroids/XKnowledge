package com.android.xknowledge.sdk.ui.event.conflict.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 内部拦截法2：子view处理事件冲突
//    private int mLastX, mLastY;
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//    ACTION_DOWN时：不交还事件；
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//ACTION_MOVE时，如果为竖向滑动，也不交还事件让ListView上下滑动，如果是横向滑动，则通过requestDisallowIntercep
//tTouchEvent(false)将事件处理权重新交给ViewPager，实现ViewPager横向滑动；
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                break;
//
//            }
//            default:
//                break;
//        }
//
//        mLastX = x;
//        mLastY = y;
//        return super.dispatchTouchEvent(event);
//    }
}
