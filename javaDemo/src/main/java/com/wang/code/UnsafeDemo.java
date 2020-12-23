package com.wang.code;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Unsafe是位于sun.misc包下的一个类，主要提供一些用于执行低级别、不安全操作的方法，如直接访问系统内存资源、
 * 自主管理内存资源等，这些方法在提升Java运行效率、增强Java语言底层资源操作能力方面起到了很大的作用。但由于
 * Unsafe类使Java语言拥有了类似C语言指针一样操作内存空间的能力，这无疑也增加了程序发生相关指针问题的风险。
 * 在程序中过度、不正确使用Unsafe类会使得程序出错的概率变大，使得Java这种安全的语言变得不再“安全”，因此对
 * Unsafe的使用一定要慎重。
 */
public class UnsafeDemo {

    /*
     * DirectByteBuffer使用Unsafe分配堆外内存，并且通过虚引用机制实现堆外内存的自动回收，下面是原理
     *
     * DirectByteBuffer的构造器在初始化的时候会创建一个cleaner的对象，cleaner对象是DirectByteBuffer对象的需引用
     * Cleaner继承自Java四大引用类型之一的虚引用PhantomReference（众所周知，无法通过虚引用获取与之关联的对象实例，
     *      且当对象仅被虚引用引用时，在任何发生GC的时候，其均可被回收），通常PhantomReference与引用队列ReferenceQueue结合使用，
     *      可以实现虚引用关联对象被垃圾回收时能够进行系统通知、资源清理等功能。如下图所示，当某个被Cleaner引用的对象将被回收时，
     *      JVM垃圾收集器会将此对象的引用放入到对象引用中的pending链表中，等待Reference-Handler进行相关处理。
     * 其中，Reference-Handler为一个拥有最高优先级的守护线程，会循环不断的处理pending链表中的对象引用，
     *      执行Cleaner的clean方法进行相关清理工作。
     * 所以当DirectByteBuffer仅被Cleaner引用（即为虚引用）时，其可以在任意GC时段被回收。当DirectByteBuffer实例对象被回收时，
     *      在Reference-Handler线程操作中，会调用Cleaner的clean方法根据创建Cleaner时传入的Deallocator来进行堆外内存的释放。
     */

    /**
     * 类Unsafe为单例实现，可以通过Unsafe.getUnsafe()获取，但是当且仅当调用getUnsafe方法的类为引导类
     * 加载器所加载时才合法，否则抛出SecurityException异常。
     * 那如若想使用这个类，该如何获取其实例？有如下两个可行方案。
     * 其一，从getUnsafe方法的使用限制条件出发，通过Java命令行命令-Xbootclasspath/a把调用Unsafe相关
     *      方法的类A所在jar包路径追加到默认的bootstrap路径中，使得A被引导类加载器加载，从而通过Unsafe.getUnsafe
     *      方法安全的获取Unsafe实例。
     *      java -Xbootclasspath/a: ${path}   // 其中path为调用Unsafe相关方法的类所在jar包路径
     * 第二种就是通过静态代码块中的反射获取unsafe对象
     */
    static Unsafe unsafe;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class Phone {

        Phone(int phoneNum){
            this.phoneNum = phoneNum;
        }
        int phoneNum;

        public int getPhoneNum(){
            return phoneNum;
        }

    }

    static class Person {
        int age;
        String name;
        Phone phone;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Integer a = 1;
        long offset = unsafe.objectFieldOffset(a.getClass().getDeclaredField("value"));
        unsafe.compareAndSwapInt(a, offset, 1, 5);
        System.out.println(a);

    }
}
