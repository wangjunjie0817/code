package com.wang;

import org.springframework.beans.factory.FactoryBean;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author WANGJJ
 * @date 2020/07/17
 */
class Test {

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target({ElementType.METHOD, ElementType.TYPE})
    public @interface CustomAnnotation{}

    @CustomAnnotation
    public static class Father{
        @CustomAnnotation
        protected Number getName(){
            return 1;
        };
    }

    public static class Son extends Father{
        @Override
        public Integer getName(){
            return 2;
        }

    }

    public interface MyInterface{

        String name = "wang";

        default String getName(){
            return "wang";
        }

        static String getNameStatic(){
            return "wang";
        }
    }

    static class BaseClass {
        public BaseClass() {}
        {
            System.out.println("I’m BaseClass class");
        }
        static {
            System.out.println("static BaseClass");
        }
    }
    static public class Base extends BaseClass {
        public Base() {}
        {
            System.out.println("I’m Base class");
        }
        static {
            System.out.println("static Base");
        }

    }

    public static void main(String[] args) {
        MyInterface.getNameStatic();

    }


}
