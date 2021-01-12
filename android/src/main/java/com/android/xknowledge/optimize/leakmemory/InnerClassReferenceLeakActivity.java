package com.android.xknowledge.optimize.leakmemory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.lang.ref.WeakReference;

/**
 * 内部类引用：
 * 方案：1.不使内部类静态成员；2.使用静态成员，但内部类声明static且activity使用弱引用
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class InnerClassReferenceLeakActivity extends TitleActivity {
    /*
     * Mistake Number 1:
     * Never create a static variable of an inner class
     */
    private static LeakyClass leakyClass;

    /**
     * 解决方案1：private LeakyClass leakyClass;
     */
//    private LeakyClass leakyClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innerclassreference_leak);

        /*
         * Inner class is defined here
         * */
        leakyClass = new LeakyClass(this);
        leakyClass.redirectToSecondScreen();
    }

    /*
     * Mistake Number 2:
     * 1. Never create a inner variable of an inner class
     * 2. Never pass an instance of the activity to the inner class
     */
//    private class LeakyClass {
//
//        private Activity activity;
//
//        public LeakyClass(Activity activity) {
//            this.activity = activity;
//        }
//
//        public void redirectToSecondScreen() {
//        }
//    }

    /*
     * 解决方案2：
     * 由于内部类实例是静态成员，非静态内存类会存在隐式外部类的引用，故改为静态内部类；
     * 内部类存在activity成员变量，则将activity改变为弱引用，避免内存泄露
     * Fix memory leaks:
     * Option 1: The class should be set to static
     * Explanation: Instances of anonymous classes do not hold an implicit reference to their outer
     * classwhen they are "static".
     * Option 2: Use a weakReference of the textview or any view/activity for that matter
     * Explanation: Weak References: Garbage collector can collect an object if only weak references
     * are pointing towards it.
     * */
    private static class LeakyClass {

        private final WeakReference<Activity> messageViewReference;

        public LeakyClass(Activity activity) {
            this.messageViewReference = new WeakReference<>(activity);
        }

        public void redirectToSecondScreen() {
            Activity activity = messageViewReference.get();
            if (activity != null) {

            }
        }
    }
}