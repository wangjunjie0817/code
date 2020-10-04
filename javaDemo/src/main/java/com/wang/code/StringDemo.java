package com.wang.code;

public class StringDemo {

    public static void main(String[] args) {

        /*
         * ---------------------------------第一部分：两个字符串是否相等-----------------------------------------
         */

        // 1 对比两个对象是否相等
        String st1 = "abc";
        String st2 = "abc";
        System.out.println(st1 == st2);    // true 很简单，程序运行时首先在常量池中创建"abc"对象，分别赋给st1和st2对象，也就是两个对象的指针相同
        System.out.println(st1.equals(st2));   // true  String重写了equals方法，对比char[]数组长度和值(jdk9以后为byte数组)   默认的Object的equals方法是 ==

        String st3 = new String("abc");
        String st4 = "abc";
        System.out.println(st3 == st4);     // false 不同的两个对象，内存地址当然不同
        System.out.println(st3.equals(st4));     // true  String重写了equals方法，对比的是char数组，相等

        String st5 = "a" + "b" + "c";
        String st6 = "abc";
        System.out.println(st5 == st6);   // true java的常量优化，编译期做了编译优化
        System.out.println(st5.equals(st6));

        String st7 = "ab";
        String st8 = "abc";
        String st9 = st1 + "c";
        System.out.println(st8 == st9);  // false
        System.out.println(st8.equals(st9));   // true

        // 以上的这些看一下编译的代码就清晰多了
        // 下面是字符串原本的写法和编译之后的结果
        // ---------源代码--------
        //String s1 = "ABCD";
        //String s2 = "A"+"B"+"C"+"D";
        //String s3 = "AB"+"CD";
        //String s4 = new String("ABCD");
        //String t  = "AB";
        //String s5 = t+"CD";
        // ---------编译代码--------
        //String s1 = "ABCD";
        //String s2 = "ABCD";
        //String s3 = "ABCD";
        //String s4 = new String("ABCD");
        //String t = "AB";
        //String s5 = (new StringBuilder(String.valueOf(t))).append("CD").toString();


        /*
         * -----------------------------第二部分：下面这句话在内存中创建了几个对象----------------------------------
         */
        // 创建了两个对象，首先在常量池中创建"abc"对象；在堆中new一个String对象，堆对象是常量池对象的拷贝副本
        String string = new String("abc");


        /*
         * -----------------------------------------第三部分：源码---------------------------------------------
         */

        /*
         * String：
         * 它是典型的 Immutable 类，被声明成为 final class，所有属性也都是 final 的。
         * 也由于它的不可变性，类似拼接、裁剪字符串等动作，都会产生新的 String 对象。
         * 由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。
         */

        /*
         * StringBuilder和StringBuffer
         * 两个类底层都是基于char数组（jdk9为byte数组）实现的，二者都继承了 AbstractStringBuilder
         * 区别仅在于最终的方法是否加了 synchronized。
         * 内部数组的大小为初始字符串长度+16，如果一开始能确定字符串的大小应该指定合适的大小，以避免多次扩容
         *
         * 初始化：初始化的内部数组的大小为16或者初始字符串长度+16
         * append方法：如果是null，则append "null"四个字符，否则将数组的值copy到自己的数组中
         * 扩容：每次扩容的大小为 (value.length << 1) + 2
         */

        new StringBuilder("wang");

        /*
         * 字符串缓存：
         * 在我们创建字符串对象并调用 intern() 方法的时候，如果已经有缓存的字符串，就会返回缓存里的实例，否则将其缓存起来。
         */

    }
}
