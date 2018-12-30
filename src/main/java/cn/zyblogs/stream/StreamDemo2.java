package cn.zyblogs.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Class: StreamDemo2.java
 * @Description: 流创建
 * @Author ZhangYB
 * @Version V1.0
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

//        从集合创建
        list.stream();
//        并行流
        list.parallelStream();

//        从数组创建
        Arrays.stream(new int[]{2,3,5});

//        创建数字流
        IntStream.of(1,2,3);
//        1到10
        IntStream.rangeClosed(1,10 );

//        使用random创建一个无限流 limit截至
        new Random().ints().limit(10);

//        自己创建流
        Random random = new Random();
        Stream.generate(()->random.nextInt()).limit(20);
    }
}
