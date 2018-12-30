package cn.zyblogs.stream;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Class: StreamDemo3.java
 * @Description:
 * @Author ZhangYB
 * @Version V1.0
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        String str = "my name is 007";

//        把每个单词的长度打印出来 filter 过滤条件
        Stream.of(str.split(" ")).filter(s -> s.length() > 2 ).map(s -> s.length()).forEach(System.out::println);

//        flatMap A -> B属性(是个集合),最终得到所有A元素里面的所有B属性集合
//        intStream/longStream并不是Stream的子类,所以需要进行装箱boxed
//        字符本身是数字 需要强转
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> System.out.println((char)i.intValue()));

//        peek用于debug 是个中间操作 和foreach是终止操作
        System.out.println("---------peek----------");
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

//        limit使用 主要用于无限流 大于100 小于1000 中的10个数字
        new Random().ints().filter(i -> i > 100 && i < 10000).limit(10).forEach(System.out::println);
    }
}
