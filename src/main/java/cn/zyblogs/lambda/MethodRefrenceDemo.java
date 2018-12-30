package cn.zyblogs.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * @Class: MethodRefrenceDemo.java
 * @Description: 方法引用
 * @Author ZhangYB
 * @Date 2018-12-29
 * @Version V1.0
 */
class Dog {

    private String name = "哮天犬";
    /**
     * 默认10斤
     */
    private int food = 10;

    public Dog() {

    }

    /**
     * 带参数的构造函数 输入String  输出dog
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * 静态方法 输入是dog 输出是void 消费者
     *
     * @param dog
     */
    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮 输入int 输出int 函数
     * JDK默认会把当前实例传入非静态方法 参数名为this ，位置是第一个 Dog this
     * 两个输入 一个输出
     *
     * @param num
     * @return 还剩多少斤
     */
    public int eat(int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {
    public static void main(String[] args) {
        // lambda表达式实际上是一个匿名函数 箭头左边是参数 右边是执行体，可缩写方法引用  Consumer<String> consumer= s -> System.out.println(s)可缩写为 Consumer<String> consumer = System.out::println;
        Dog dog = new Dog();
        dog.eat(3);

        Consumer<String> consumer = System.out::println;
        consumer.accept("接收的数据");

//         静态方法的方法引用 类名::方法名
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(dog);

        //  非静态方法 使用对象实例来引用 输入和输出类型一样 可以修改为一元函数接口
        // Function<Integer , Integer> function = dog::eat;
        // 一元运算符UnaryOperator 可修改为Int
//        UnaryOperator<Integer> function = dog::eat;
        IntUnaryOperator function = dog::eat;

        // apply将此函数应用于给定的参数。
//        System.out.println("还剩下" + function.apply(2) + "斤");
        // 使用IntUnaryOperator applyAsInt将此运算符应用于给定的操作数
        System.out.println("还剩下" + function.applyAsInt(2) + "斤");

//        Dog::eat; BiFunction表示接受两个参数并生成结果的函数
//        非静态方法 使用类名来方法引用
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("---还剩下" + eatFunction.apply(dog, 2) + "斤");

//        构造函数的方法引用 空参没有输入 只有输出的函数 supplier供应
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象:" + supplier.get());

//        带参数的构造函数的方法引用
        Function<String, Dog> function1 = Dog::new;
        System.out.println("创建了新对象:" + function1.apply("有才"));

        List<String> list = new ArrayList<>();
        list.add("hello");
        test(list);
        // 不受影响
        System.out.println(list);
    }

    /**
     *  java参数传值引用
     * @param list
     */
    public static void test(List<String> list){
        list.add("test");
        list = null;

    }
}
