package com.android.xknowledge.sdk.ui.event.conflict.viewpager;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewPager事件冲突实践
 * 参考：
 * 享学2《事件冲突原因或解决方案大解密》
 */
public class ViewPagerConflictActivity extends TitleActivity {

    private int[] iv = new int[]{R.mipmap.viewpage_conflict_0, R.mipmap.viewpage_conflict_1, R.mipmap.viewpage_conflict_2,
            R.mipmap.viewpage_conflict_3, R.mipmap.viewpage_conflict_4, R.mipmap.viewpage_conflict_5,
            R.mipmap.viewpage_conflict_6, R.mipmap.viewpage_conflict_7, R.mipmap.viewpage_conflict_8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_conflic);
        BadViewPager pager = findViewById(R.id.viewpager_conflit_viewpager);

        List<Map<String, Integer>> strings = new ArrayList<>();
        Map<String, Integer> map;

        for (int i = 0; i < 20; i++) {
            map = new HashMap<>();
            map.put("key", iv[i % 9]);
            strings.add(map);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(this, strings);
        pager.setAdapter(adapter);
    }
}
