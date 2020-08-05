package com.wang;


import com.wang.pojo.Man;
import com.wang.pojo.Person;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WANGJJ
 * @date 2020/06/23
 */
public class AppTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
