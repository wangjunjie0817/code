package com.wang.code;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.WeakHashMap;

public class RefDemo {

    public static void main(String[] args) throws InterruptedException {
        /*
         * 强引用：
         * （“Strong” Reference），就是我们最常见的普通对象引用，只要还有强引用指向一个对象，
         * 就能表明对象还“活着”，垃圾收集器不会碰这种对象。对于一个普通的对象，如果没有其他的引用关系，
         * 只要超过了引用的作用域或者显式地将相应（强）引用赋值为 null，就是可以被垃圾收集的了，当然
         * 具体回收时机还是要看垃圾收集策略。
         */
        Object strongRef = new Object(); // 创建强引用
        strongRef = null;  // 强引用的回收：超出引用的作用域 或者 手动置为null

        /*
         * 软引用：用于对象的缓存，对象的强引用被删除了之后，软引用引用的对象并不会被回收，而是内存不足才会回收
         * 软引用是相对于强引用弱一些的引用，jvm会在内存不足的时候尝试回收弱引用的对象
         * JVM 会确保在抛出 OutOfMemoryError 之前，清理软引用指向的对象。
         * 软引用通常用来实现内存敏感的缓存，如果还有空闲内存，就可以暂时保留缓存，当内存不足时清理掉，
         * 这样就保证了使用缓存的同时，不会耗尽内存。
         */

        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        //将缓存数据的强引用去除
        cacheData = null;
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //在分配一个120M的对象，看看缓存对象的回收情况
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());

        //上面的代码最终的输出：
        //第一次GC前null
        //第一次GC前[B@7d4991ad
        //[GC (System.gc())  105728K->103248K(175104K), 0.0009623 secs]
        //[Full GC (System.gc())  103248K->103139K(175104K), 0.0049909 secs]
        //第一次GC后null
        //第一次GC后[B@7d4991ad
        //[GC (Allocation Failure)  103805K->103171K(175104K), 0.0027889 secs]
        //[GC (Allocation Failure)  103171K->103171K(175104K), 0.0016018 secs]
        //[Full GC (Allocation Failure)  103171K->103136K(175104K), 0.0089988 secs]
        //[GC (Allocation Failure)  103136K->103136K(199680K), 0.0009408 secs]
        //[Full GC (Allocation Failure)  103136K->719K(128512K), 0.0082685 secs]
        //分配后null
        //分配后null

        /*
         * 弱引用：弱引用的对象的生命周期是如果没有强引用了，弱引用对象在下一次GC就会被收集
         * 弱引用（WeakReference）并不能使对象豁免垃圾收集，仅仅是提供一种访问在弱引用状态下对象的途径。
         * 这就可以用来构建一种没有特定约束的关系，比如，维护一种非强制性的映射关系，如果试图获取时对象还在，
         * 就使用它，否则重现实例化。它同样是很多缓存实现的选择。
         */
        byte[] cacheData1 = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef1 = new WeakReference<>(cacheData1);
        System.out.println("第一次GC前" + cacheData1);
        System.out.println("第一次GC前" + cacheRef1.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData1);
        System.out.println("第一次GC后" + cacheRef1.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第二次GC后" + cacheData1);
        System.out.println("第二次GC后" + cacheRef1.get());

        /*
         * 弱引用的应用场景：WeakHashMap
         * 传统的HashMap有一个问题是：
         * 当指向key对象的引用已经没有了，此时想将map中的key和value都删除，这种情况下
         * 由于map包含了对象的引用，所以key和value的对象不能被回收，导致内存泄漏
         *
         * WeakHashMap原理解析：
         * 1 WeakHashMap的Entry继承自WeakReference，为key的弱引用
         * 2 Entry的构造函数中包含一个ReferenceQueue
         * 3 当key的对象被回收，会讲key对象放入ReferenceQueue
         * 4 在我们每次调用WeakHashMap时，WeakHashMap会检查ReferenceQueue中的是否为空，不为空则删除Entry和key对象
         */

        Object key = new Object();
        Object val = new Object();
        WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(key, val);
        System.out.println(weakHashMap.get(key));
        key = null;
        System.gc();
        System.out.println(weakHashMap.get(key));

        /*
         * 虚引用：
         * 对于幻象引用，有时候也翻译成虚引用，你不能通过它访问对象。幻象引用仅仅是提供了一种确保对象被 finalize 以后，
         * 做某些事情的机制，比如，通常用来做所谓的 Post-Mortem 清理机制，我在专栏上一讲中介绍的 Java 平台自身 Cleaner 机制等，
         * 也有人利用幻象引用监控对象的创建和销毁。
         *
         * DirectByteBuffer中有虚引用的应用
         * 在DirectByteBuffer的构造法法中会讲DirectByteBuffer为创建cleaner对象
         * cleaner对象继承了PhantomReference，当DirectByteBuffer会垃圾回收时
         * Reference对象创建时会建立一个线程，线程会回调cleaner的clean方法实现内存的回收
         */

        Object counter = new Object();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
        counter = null;
        System.gc();
        try {
            // Remove 是一个阻塞方法，可以指定 timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                // do something
            }
        } catch (InterruptedException e) {
            // Handle it
        }



    }
}
