package com.android.xknowledge.other;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hook：Java反射实现
 * 参考：https://www.jianshu.com/p/4f6d20076922
 */
public class HookActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        Button button = findViewById(R.id.hook_button_onclick);
        button.setOnClickListener(v -> {
            Toast.makeText(this, "HookActivity", Toast.LENGTH_SHORT).show();
        });

        hookOnClickListener(button);
    }

    /**
     * 实现view的onClick事件的监听
     */
    @SuppressLint("PrivateApi")
    private void hookOnClickListener(View view) {
        try {
            //获取view的ListenerInfo对象
            Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfoMethod.setAccessible(true);
            Object listenerInfo = getListenerInfoMethod.invoke(view);

            //获取ListenerInfo对象的mOnClickListener成员
            Class<?> listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
            Field onClickListenerField = listenerInfoClass.getField("mOnClickListener");
            onClickListenerField.setAccessible(true);
            View.OnClickListener originalClickListener = (View.OnClickListener) onClickListenerField
                    .get(listenerInfo);

            //通过Hook"代理"类，在调用原始的ClickListener之前，插入Hook相关的逻辑
            HookClickListener hookClickListener = new HookClickListener(originalClickListener);
            onClickListenerField.set(listenerInfo, hookClickListener);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * HookClickListener，底层调用originalOnClickListener，之前添加Hook API
     */
    class HookClickListener implements View.OnClickListener {
        private View.OnClickListener originalOnClickListener;

        public HookClickListener(View.OnClickListener originalOnClickListener) {
            this.originalOnClickListener = originalOnClickListener;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(HookActivity.this, "hook click", Toast.LENGTH_SHORT).show();
            if (originalOnClickListener != null) {
                originalOnClickListener.onClick(v);
            }
        }
    }
}