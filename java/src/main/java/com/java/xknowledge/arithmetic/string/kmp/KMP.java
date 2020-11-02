package com.java.xknowledge.arithmetic.string.kmp;

/**
 * 字符串KMP查找算法
 * 参考：《享学1：19.字符串》
 */
class KMP {
    public static void kmp(String s, String p) {
        int[] next = buildNext(p);

        int sLength = s.length();//主串长度
        int pLength = p.length();//子串长度
        if (sLength < pLength) {
            System.out.println("Error.The main string is greater than the sub string length.");
            return;
        }

        int i = 0;
        int j = 0;
        while (i < sLength && j < pLength) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;//如果相等i,j依次向后比较
                j++;
            } else {
                //=============第[24]回合================
                //                 | => 17
                //BBC ABCDAB ABCDABCDABDE
                //           ABCDABD
                //S[17]{C} != P[6]{D}
                //因此
                //i不变 =17
                //j = next[6] = 2//引起匹配字符串真前缀和真后缀相等为2位，故j=next[6]。
                //               //保证原来主串匹配串...ABCD[AB]CD...真后缀，和子串[AB]CDABD真后缀相等[AB]
                //               //接下来沿着主串继续依次比较接口
                //=============第[25]回合================
                //                 | => 17
                //BBC ABCDAB ABCDABCDABDE
                //               ABCDABD
                //               ABC
                //S[17]{C} == P[2]{C}
                //因此
                //i++ =18
                //j++ =3
                //=============第[26]回合================
                j = next[j];
            }
        }

        int index = -1;//成功匹配的位置
        if (j >= pLength) {//如果匹配成功
            index = i - j;
            System.out.println("Successful math index is :" + index);
        } else {
            System.out.println("Faild math");
        }
    }

    /**
     * 构建next表
     * 查找真前缀==真后缀的最大长度，以获取模式串尽量多往右移动
     */
    private static int[] buildNext(String p) {
        int[] N = new int[p.length()];

        int m = p.length();
        int j = 0;//获取真前缀和真后缀的位数
        int t = N[0] = -1;//真前缀和真后缀相等的位数

        while (j < m - 1) {
            //P = ABCDABD
            //j = 0, prefix(P, 0) = φ
            //next[0] = -1;//规定如此
            //
            //P = ABCDABD
            //j = 1, prefix(P, 1) = A
            //真前缀: φ 真后缀: φ
            //next[1] = 0;
            //
            //P = ABCDABD
            //j = 2, prefix(P, 2) = AB
            //真前缀: A 真后缀: B
            //next[2] = 0;
            //
            //P = ABCDABD
            //j = 3, prefix(P, 3) = ABC
            //真前缀: A,AB 真后缀: BC,C
            //next[3] = 0;
            //P = ABCDABD
            //j = 4, prefix(P, 4) = ABCD
            //真前缀: A,AB,ABC 真后缀: BCD,CD,D
            //next[4] = 0;
            //
            //P = ABCDABD
            //j = 5, prefix(P, 5) = ABCDA
            //真前缀: A,AB,ABC,ABCD 真后缀: BCDA,CDA,DA,A
            //next[5] = 1;
            //
            //P = ABCDABD
            //j = 6, prefix(P, 6) = ABCDAB
            //真前缀: A,AB,ABC,ABCD,ABCDA 真后缀: BCDAB,CDAB,DAB,AB,B
            //next[6] = 2;
            if (t < 0 || p.charAt(j) == p.charAt(t)) {//从头前缀和尾后缀进行字符匹配
                j++;
                t++;
                N[j] = t;
            } else {
                t = N[t];
            }
        }
        return N;
    }
}
