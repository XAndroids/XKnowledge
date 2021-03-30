package com.java.xknowledge.se.thread.demo.qpcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QPCacheManager {
    public Map<Integer, QPCache> testMap = new ConcurrentHashMap<>();

    static class TestCacheHolder {
        private static final QPCacheManager INSTANCE = new QPCacheManager();
    }

    public static QPCacheManager getInstance() {
        return TestCacheHolder.INSTANCE;
    }

    public synchronized QPCache getTest(int i) {
        QPCache QPCache = testMap.get(i);
        if (QPCache == null) {
            QPCache = new QPCache();
            testMap.put(i, QPCache);
        }

        return QPCache;
    }

    public String getTestQPString(int i) {
        QPCache QPCache = getTest(i);
        return QPCache.getQPString();
    }

    public void putTestQPString(int i, String qpString) {
        QPCache QPCache = getTest(i);
        QPCache.setQPString(qpString);
    }
}
