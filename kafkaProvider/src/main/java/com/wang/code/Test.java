package com.wang.code;

import java.util.TreeSet;
import static com.wang.code.Test.Test1;

/**
 * @author WANGJJ
 * @date 2020/07/27
 */
public class Test {

    public static class Test1{
        static String name$wang = "wang";
    }

    static class Person {
        public Person() {};
        String name = "No name";
        public Person(String nm) {
            name = nm;
        }
    }
    static class Employee extends Person {
        static {
            name = "wang";
        }
        static String name ;
        public Employee() {

        }
    }

    public static void main(String[] args) {

        Employee e = new Employee();
        System.out.println(e.name);

    }
}
