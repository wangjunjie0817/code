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

        // Class<String> stringClass = String.class;
        // stringClass.get***

        /*
         * 动态代理
         * 动态代理是利用反射机制在运行时创建代理类
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
         */

        /*
         * 实现动态代理主要的方式： 基于接口的 JDK Proxy、ASM、CGlib(基于ASM)、Javassist
         */


    }
}
