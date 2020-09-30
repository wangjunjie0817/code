package com.wang.code;

import com.sun.deploy.util.StringUtils;
import com.sun.jmx.snmp.SnmpNull;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.misc.Unsafe;

import java.util.*;

/**
 * @author WANGJJ
 * @date 2020/07/29
 */
public class Demo {

    private static HashMap<Integer, Long> map = new HashMap<>();

    static int count = 0;

    static final int maxValue = 6;

    public static void main(String[] args) {
        int[] a = {4};
        int[] b = {5};
        System.out.println(canCompleteCircuit(a, b));
    }

    public static int canCompleteCircuit (int[] gas, int[] cost) {
        // write code here
        int startIndex = 0;
        int residue = 0;
        int result = -1;
        boolean flag = true;
        while(startIndex < gas.length){
            for(int i = startIndex; i < gas.length; i++){
                residue += gas[i];
                residue -= cost[i];
                if(residue < 0){
                    flag = false;
                }
            }
            for(int i = 0; i < startIndex; i++){
                residue += gas[i];
                residue -= cost[i];
                if(residue < 0){
                    flag = false;
                }
            }
            if (flag){
                result = startIndex;
                return result;
            } else {
                flag = true;
                startIndex++;
            }

        }
        return result;
    }

    public static void dice(int n, int[] resultArray){
        for (int i = 1; i <= maxValue; ++i){
            dice(n, n, i, resultArray);
        }
    }

    public static void dice(int n, int current, int sum, int[] resultArray){
        if (current == 1){
            resultArray[sum - n + 1]++;
        }
        else {
            for (int i = 1; i <= maxValue; ++i){
                dice(n, n - 1, sum + i, resultArray);
            }
        }
    }



    /**
     * 归并排序
     */
    private static void sort(int[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(int[] a, int lo, int mid, int hi){

        int i = lo;
        int j = mid + 1;
        int[] aux = new int[a.length];
        for (int k = lo; k <= hi; k++ ){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid){a[k] = aux[j++];}
            else if (j > hi) {a[k] = aux[i++];}
            else if (a[i] < a[j]){a[k] = aux[i++];}
            else {a[k] = aux[j++];}
        }
    }

    /**
     * 测试数据：
     * int[][] intMatrix =
     *                {
     *                {1, 10, 3, 8},
     *                {12, 2, 9, 6},
     *                {5, 7, 4, 11},
     *                {3, 7, 16, 5}
     *                };
     *
     *         System.out.println(figureOutMax(intMatrix, 3, 3));
     * f(i, j) = max(f(i - 1, j) + value(i, j), f(i, j - 1) + value(i, j))
     * @param intMatrix 矩阵
     */
    public static int figureOutMax(int[][] intMatrix, int i, int j){

        if (i == 0 && j == 0){
            return intMatrix[i][j];
        }

        if (i == 0){
            return intMatrix[i][j] + figureOutMax(intMatrix, i, --j);
        }
        if (j == 0){
            return intMatrix[i][j] + figureOutMax(intMatrix, --i, j);
        }
        if (intMatrix[i][j - 1] > intMatrix[i - 1][j]){
            return intMatrix[i][j] + figureOutMax(intMatrix, i, --j);
        } else {
            return intMatrix[i][j] + figureOutMax(intMatrix, --i, j);
        }



    }

    /***
     * 求数字数组中的数字翻译成字母之后的方案数
     */
    public static void test(String numString, List<String> strings){

        if (numString.length() < 2){
            strings.add(numString);
            System.out.println(strings);
            count++;
            return;
        }

        for (int i = 1; i <= 2; i++){
            String subStringBefore = numString.substring(0, i);
            if (0 < Integer.parseInt(subStringBefore) && 26 >= Integer.parseInt(subStringBefore)){
                strings.add(subStringBefore);
                String subStringAfter = numString.substring(i);
                test(subStringAfter, new ArrayList<>(strings));
            }
        }


    }

    public static char getCharWithInt(int i){
        return (char) (i + 97);
    }

    public static void combination(char[] arrays, int start) {
        if (start == arrays.length - 1){
            System.out.println(Arrays.toString(arrays));
            return;
        }
        char arrayStart = arrays[start];
        for (int i = start; i < arrays.length; i++){
            arrays[start] = arrays[i];
            arrays[i] = arrayStart;
            combination(arrays, start + 1);
            char temp = arrays[i];
            arrays[i] = arrays[start];
            arrays[start] = temp;
        }
    }


    /**
     * 求数组中子序列的最大和
     */
    public static void maxSum(){
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};

        int maxSum = 0;

        int maxSumTemp = 0;

        for (int value : array) {
            maxSumTemp += value;
            if (maxSumTemp < 0) {
                maxSumTemp = 0;
            }
            if (maxSumTemp > maxSum) {
                maxSum = maxSumTemp;
            }
        }

        System.out.println(maxSumTemp);
    }

    /**
     * 查找数组中的最小的K个数
     *
    */
    static int search(int[] array, int lo, int hi, int k){
        int standard = array[lo];

        int i = lo;
        int j = hi;

        while (i < j){
            while (array[j] >= standard && i < j){
                j--;
            }
            while (array[i] <= standard && i < j){
                i++;
            }
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        array[lo] = array[i];
        array[i] = standard;

        if (i < k){
            return search(array, i + 1, hi, k);
        } else if (i > k){
            return search(array, lo, i - 1, k);
        } else {return i;}
    }

    public static void listAll(List candidate, String prefix)
    {

        System.out.println(prefix);
        for(int i=0;i<candidate.size();i++)
        {
            List temp = new LinkedList(candidate);
            listAll(temp,prefix+temp.remove(i));
        }
    }



//    static List<Character> combination(char[] chars, int m, int index, boolean withFirst, List<Character> cache){
//
//        if (m == 0){
//            return cache;
//        }
//        if (withFirst){
//            cache.add(chars[index]);
//            combination(chars, m-1, ++index, true, cache);
//            combination(chars, m-1, ++index, false, cache);
//        } else {
//            combination(chars, m, ++index, true, cache);
//            combination(chars, m, ++index, false, cache);
//        }
//    }


    static ListNode mergeList(ListNode listNode1, ListNode listNode2){

        if (listNode1 == null && listNode2 != null){
            return listNode2;
        }

        else if (listNode2 == null && listNode1 != null){
            return listNode1;
        } else if (listNode2 == null && listNode1 == null){
            return null;
        }


        boolean flag = listNode1.getVal() > listNode2.getVal();

        ListNode head = flag ? listNode2 : listNode1;

        head.next = mergeList(flag?listNode1:listNode1.next, flag?listNode2.next:listNode2);

        return head;
    }

    static ListNode reverseLinkList(ListNode listNode, ListNode next){

        if (next == null){
            return listNode;
        }

        ListNode nextNext = next.next;

        next.next = listNode;
        return reverseLinkList(next, nextNext);
    }

    static List<Integer> test(List<Integer> list){
        return list;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }

        public int getVal(){
            return val;
        }
    }

    static void binarySearch(int[][] ints, int low, int high, int num) {
        if (low > high) {
            return;
        }
        int mid = (low + high) / 2;
        int[] ints1 = ints[mid];
        if (ints1[0] > num) {
            binarySearch(ints, low, mid - 1, num);
        }
        if (ints1[ints1.length - 1] < num) {
            binarySearch(ints, mid + 1, high, num);
        }
        binarySearch1(ints[mid], 0, ints[mid].length - 1, num);
    }

    static void binarySearch1(int[] ints, int low, int high, int num) {
        if (low > high) {
            return;
        }
        int mid = (low + high) / 2;
        if (ints[mid] == num) {
            System.out.println(true);
        }
        binarySearch1(ints, low, mid - 1, num);
        binarySearch1(ints, mid + 1, high, num);
    }


    static void sortArray(int[] ints, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int standard = ints[lo];
        int i = lo;
        int j = hi;
        while (i < j) {
            while (ints[j] >= standard && i < j) {
                j--;
            }
            while (ints[i] <= standard && i < j) {
                i++;
            }
            int temp1 = ints[j];
            ints[j] = ints[i];
            ints[i] = temp1;
        }
        ints[lo] = ints[i];
        ints[i] = standard;
        sortArray(ints, lo, i - 1);
        sortArray(ints, i + 1, hi);

    }

    ;

}
