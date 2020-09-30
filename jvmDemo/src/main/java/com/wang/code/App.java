package com.wang.code;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * Hello world!
 *
 */
public class App{

    static class ClassA{
        public void println(String var){
            System.out.println(var);
        }
    }

    public static void main( String[] args ) throws Throwable {
        ClassA classA = new ClassA();
        MethodType type = MethodType.methodType(void.class, String.class);

        MethodHandle println = lookup().findVirtual(ClassA.class, "println", type).bindTo(classA);
        println.invokeExact("wangjunjie");

    }
}
