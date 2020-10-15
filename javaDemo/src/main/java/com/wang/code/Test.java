package com.wang.code;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clz = ThreadLocalRandom.class;
        Method nextSecondarySeed = clz.getDeclaredMethod("nextSecondarySeed");
        nextSecondarySeed.setAccessible(true);

        int i = 1;
        int j = 1;

        for (int k=0; k<1000; k++){
            if (((int)nextSecondarySeed.invoke(clz) & 0x80000001) == 0){
                i++;
            } else {
                j++;
            }
//            System.out.println((int)nextSecondarySeed.invoke(clz) & 0x80000001);
        }

        System.out.println(i);
        System.out.println(j);


    }

}
