package cn.zyblogs.stream;

import java.util.stream.IntStream;

/**
 * @Class: StreamDemo1.java
 * @Description: 流处理
 * @Author ZhangYB
 * @Version V1.0
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] nums = { 1 , 2 , 3 };
//        外部迭代 串行  数据量大 性能不达标
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        System.out.println("结果为:" + sum);
//        Stream的内部迭代
//        map就是中间操作(返回Stream的操作)
//        sum就是终止操作 看返回值类型
//        int sum1 = IntStream.of(nums).map(i -> i * 2 ).sum();
        int sum2 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println("结果为:" + sum2);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i){
        System.out.println("执行了乘以2");
        return i * 2;
    }
}
