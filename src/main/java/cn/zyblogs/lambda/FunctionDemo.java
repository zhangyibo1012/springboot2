package cn.zyblogs.lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @Class: FunctionDemo.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class FunctionDemo {
    public static void main(String[] args) {

        // 断言函数接口
        Predicate<Integer> predicate = i -> i > 0;
        System.out.println(predicate.test(-9));
        IntPredicate intPredicate = i -> i > 0;
        System.out.println(intPredicate.test(-9));

        // 消费函数接口
        // IntConsumer
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");
    }
}
