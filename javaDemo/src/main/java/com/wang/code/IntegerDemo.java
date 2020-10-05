package com.wang.code;

public class IntegerDemo {

    public static void main(String[] args) {
        /*
         * 编译阶段、运行时，自动装箱 / 自动拆箱是发生在什么阶段？
         * 使用静态工厂方法 valueOf 会使用到缓存机制，那么自动装箱的时候，起作用吗？
         * 为什么我们需要原始数据类型，Java 的对象似乎也很高效，应用中具体会产生哪些差异？
         *
         * 原始数据类型和 Java 泛型并不能配合使用，也就是Primitive Types 和Generic 不能混用，
         * 于是JAVA就设计了这个auto-boxing/unboxing机制，实际上就是primitive value 与 object之间的隐式转换机制，
         * 否则要是没有这个机制，开发者就必须每次手动显示转换，那多麻烦
         *
         * 首先，自动装箱拆箱发生在编译阶段，在使用的时候尽量使用原始类型
         * javac 替我们自动把装箱转换为 Integer.valueOf()，把拆箱替换为 Integer.intValue()
         * 其次，这种缓存机制不只是Integer有同样存在于其他的一些包装类，比如：
         * Boolean，缓存了 true/false 对应实例，确切说，只会返回两个常量实例 Boolean.TRUE/FALSE。
         * Short，同样是缓存了 -128 到 127 之间的数值。
         * Byte，数值有限，所以全部都被缓存。
         * Character，缓存范围’\u0000’ 到 ‘\u007F’。
         *
         * 缓存机制：
         * 使用静态工厂方法 valueOf 会使用到缓存机制，对于-128～127范围内的Integer对象，直接返回缓存对象，否则返回new Integer(int num)
         * 缓存机制实现在IntegerCache 的静态初始化块
         *
         * Integer 源码
         * toString
         * 缓存
         * parseInt
         * valueOf
         */

    }
}
