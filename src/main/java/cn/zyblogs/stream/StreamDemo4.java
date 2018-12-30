package cn.zyblogs.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Class: StreamDemo4.java
 * @Description: 终止操作
 * @Author ZhangYB
 * @Version V1.0
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        String str = "my name is 007";

//        使用并行流,打开并行流 parallel() 输出结果乱序
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println();

//        使用 forEachOrdered 保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));
        System.out.println();

//        收集到 list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

//        使用 reduce 拼接字符串
        Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
//        reduce.orElse("") 如果 reduce 为null  返回空字符串"" 不为空 返回reduce值
        System.out.println(reduce.orElse(""));

//        带初始化的reduce
        String reduce1 = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce1);

//        计算所有单词总长度 map reduce
        Integer length = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(length);

//            max 的使用
//        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        Optional<String> max = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println(max.get());

//        使用 findFirst 短路操作
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }
}
