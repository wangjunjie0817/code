package com.wang.code.xiangxue.forkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class ForkJoinTest {

    // 内部类生成长度100000的数组
    public static class MakeArray {

        static final Integer ARRAY_LENGTH =  100000;

        static int[] makeArray () {
            Random random = new Random();
            int[] ints = new int[ARRAY_LENGTH];
            for (int i=0; i<ARRAY_LENGTH; i++) {
                ints[i] = random.nextInt(ARRAY_LENGTH * 3);
            }
            return ints;
        }

    }

    private static class SumTask extends RecursiveTask<Integer>{

        // fork/join拆分最小的阈值
        private final static Integer THRESHOLD = MakeArray.ARRAY_LENGTH / 10;

        int[] numArray;
        Integer fromIndex;
        Integer toIndex;

        SumTask(int[] numArray, Integer fromIndex, Integer toIndex) {
            this.numArray = numArray;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {

            int count = 0;

            if (toIndex - fromIndex < THRESHOLD){
                for (int i = fromIndex; i<toIndex;i++) {
                    count += numArray[i];
                }
                return count;
            } else {
                int mid = (toIndex + fromIndex) /2;
                SumTask left = new SumTask(numArray, fromIndex, mid);
                SumTask right = new SumTask(numArray, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        SumTask sumTask = new ForkJoinTest.SumTask(ForkJoinTest.MakeArray.makeArray(), 0, MakeArray.ARRAY_LENGTH-1);

        long start = System.currentTimeMillis();
        Integer sum = pool.invoke(sumTask);
        System.out.println(sum);
        System.out.println("Task is Running.....");

        System.out.println("The count is "+sumTask.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");


    }

}
