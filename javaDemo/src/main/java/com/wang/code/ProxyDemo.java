package com.wang.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {

    public interface Hello {
        void sayHello();
    }
    public static class HelloImpl implements Hello {

        @Override
        public void sayHello(){
            System.out.println("hello");
        };
    }

    public static class ProxyHandler implements InvocationHandler{

        public Object object;

        public ProxyHandler(Object object){
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke "  + method.getName());
            Object result = method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) {

        /*
         * 首先概念：
         * 强类型：在不同类型的变量赋值时，是否需要显示的类型转换
         * 静态语言：类型检查是在编译器检查
         * java呢，是强类型静态语言，但是由于提供了反射等机制，所以具备部分动态语言的特性
         */

        /*
         * 反射机制
         * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
         * 对于任意一个对象，都能够调用它的任意方法和属性；
         * 这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制。
         */

        /*
         * 动态代理
         * 动态代理是利用反射机制在运行时创建代理类, 说的这么玄乎，那么动态代理到底是什么概念？
         * 其实本质上就是在特定的时机去修改已经有的类型或者创建新的类型。
         *
         * 按照目前的理解，动态代理主要用到的技术包括：反射和类加载机制
         * 第一、类加载机制提供了我们可以通过二进制流通过ClassLoader的defineClass生成Class<?>，通过这一步实现了字节码到类加载的过程
         *      protected final Class<?> defineClass(
         *              String name, byte[] b, int off, int len,ProtectionDomain protectionDomain)
         * 其次、通过newInstance实例化可以传入Object...参数，从而实现类的增强
         * 我们在InvocationHandler中定义增强的语句，这样生成的代理对象就是有增强的功能的实例
         *
         *
         * JDK Proxy 的优势：
         * 最小化依赖关系，减少依赖意味着简化开发和维护，JDK 本身的支持，可能比 cglib 更加可靠。
         * 平滑进行 JDK 版本升级，而字节码类库通常需要进行更新以保证在新版 Java 上能够使用。
         * 代码实现简单。
         *
         * 基于类似 cglib 框架的优势：
         * 有的时候调用目标可能不便实现额外接口，从某种角度看，限定调用者实现接口是有些侵入性的实践，类似 cglib 动态代理就没有这种限制。
         * 只操作我们关心的类，而不必为其他相关类增加工作量。
         * 高性能。
         */

        Hello hello = new HelloImpl();
        InvocationHandler proxyHandler = new ProxyHandler(hello);
        Hello proxyInstance = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                proxyHandler);

        proxyInstance.sayHello();

        /*
         * 动态代理原理：
         * newProxyInstance ——> getProxyClass0获取到class对象  ——> ProxyClassFactor.apply()  ——> generateProxyClass()
         * ——> 通过反射获取代理类的构造器 ——> cons.newInstance(new Object[]{h})生成代理类
         *
         * -newProxyInstance: 这个方法是整个动态代理的入口，通过这个方法可以获取到动态代理的对象，这里注意参数
         * ----Class<?> cl = getProxyClass0(loader, intfs) 通过这个逻辑实现了字节码 --> 类加载的过程
         * --------proxyClassCache.get(loader, interfaces) : 这里这个proxyClassCache是一个很关键的对象
         * ------------proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
         *                ProxyClassFactory 中的apply方法实现了通过接口调用classLoader生成class<>的逻辑
         *                KeyFactory是缓存的key，可以看成是classLoader + intfs构成的key
         * ----------------byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);
         * ----------------return defineClass0(loader, proxyName,proxyClassFile, 0, proxyClassFile.length);
         * ----Constructor<?> cons = cl.getConstructor(constructorParams); 获取上面cl的构造器
         * ----cons.newInstance(new Object[]{h})   native方法，jvm层面实现了通过h作为参数完成实例化对象过程
         */


        /*
         * 实现动态代理主要的方式： 基于接口的 JDK Proxy、ASM、CGlib(基于ASM)、Javassist
         */


    }
}
