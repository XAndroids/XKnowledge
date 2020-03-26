package com.xposed.xknowledge;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.main_xposed_text);
        textView.setText(getTextString());
    }

    //被Hook的方法，"劫持"后返回新的结果
    private String getTextString() {
        return "XPosed";
    }
}
