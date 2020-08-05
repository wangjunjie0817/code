package com.wang.code.one;

import java.util.Stack;

/**
 * @author WANGJJ
 * @date 2020/04/18
 */
public class InFixToRightFix {

    public static void main(String[] args) {
        String[] strings = {"(", "(", "1", "+", "2", ")" , "*" , "(", "(", "3", "-", "4", ")", "*", "(", "5", "-", "6", ")", ")", ")"};
        for (String s : strings) {
            System.out.print(s);
        }
        System.out.println();
        inFixToRightFix(strings);

    }

    private static void inFixToRightFix(String[] strings) {
        Stack<String> stringStackNum = new Stack<>();
        Stack<String> stringStackSymbol = new Stack<>();

        for (String s: strings) {
            if ("(".equals(s)) {
                continue;
            }
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                stringStackSymbol.push(s);
            } else if (")".equals(s)) {
                String pop1 = stringStackNum.pop();
                String pop2 = stringStackNum.pop();
                stringStackNum.push(pop2 + pop1 + stringStackSymbol.pop());
            }else {
                stringStackNum.push(s);
            }
        }

        for (String s : stringStackNum) {
            System.out.print(s);
        }

    }
}

// 12+34-56-**