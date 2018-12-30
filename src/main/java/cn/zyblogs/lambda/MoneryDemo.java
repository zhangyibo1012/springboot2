package cn.zyblogs.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @Class: MoneryDemo.java
 * @Description: 定义接口的输入和输出类型
 * @Author ZhangYB
 * @Version V1.0
 */

/**
 * interface IMoneryFormat{
 * String format(int i );
 * }
 */

class MyMonery {
    private final int monery;

    public MyMonery(int monery) {
        this.monery = monery;
    }

    public void printMonery(Function<Integer, String> moneryFormat) {
        // apply将此函数应用于给定的参数。
        System.out.println("存款:" + moneryFormat.apply(this.monery));
    }
}

public class MoneryDemo {
    public static void main(String[] args) {
        MyMonery myMonery = new MyMonery(9999999);
        myMonery.printMonery(i -> new DecimalFormat("#,###").format(i));

        Function<Integer, String> myFormat = i -> new DecimalFormat("#,###").format(i);
        // 函数接口链式调用
        myMonery.printMonery(myFormat.andThen(s -> " 人民币 " + s));

    }
}
