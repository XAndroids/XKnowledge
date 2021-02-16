package com.java.xknowledge.arithmetic.find.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器实践
 * 运行：
 * 已匹配数量 1000309    //存在误判309个
 *
 * Process finished with exit code 0
 * 参考：https://juejin.cn/post/6844904007790673933
 */
class BloomFilterTest {
    public static void main(String[] args) {
        int total = 1000000;
        //fpp可以控制误判率，但是减少误判率的值，需要的存储控件更大！！！！
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,
                0.0002);
        for (int i = 0; i < total; i++) {
            bloomFilter.put("" + i);
        }

        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bloomFilter.mightContain("" + i)) {
                count++;
            }
        }

        System.out.println("已匹配数量 " + count);
    }
}
