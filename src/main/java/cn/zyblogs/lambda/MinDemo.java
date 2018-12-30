package cn.zyblogs.lambda;

import java.util.stream.IntStream;

/**
 * @Class: MinDemo.java
 * @Description: lambda表达式 取最小值
 * @Author zyb
 * @Version V1.0
 */
public class MinDemo {
    public static void main(String[] args) {
        // 假设有很多数字 单线程效率达不到 需要利用多线程去查找 把数组拆分 多个线程比较 最后汇总结果比较
        int[] nums = {33, 55, -55, 90, -666, 90};
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println(min);

        // jdk8 假设很多数字  只需要加入parallel() 并行流去执行
        final int min2 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);
    }
}
