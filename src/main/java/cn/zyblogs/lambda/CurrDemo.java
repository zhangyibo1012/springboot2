package cn.zyblogs.lambda;

import java.util.function.Function;

/**
 * @Class: CurrDemo.java
 * @Description: 级联表达式
 *              柯里化:把多个参数的函数转换为只有一个参数的函数
 *              柯里化的目的:函数表转化
 * @Author ZhangYB
 * @Version V1.0
 */
public class CurrDemo {
    public static void main(String[] args) {
//        输入一个参数 返回一个函数 级联表达式
        Function<Integer , Function<Integer , Integer>> fun = x -> y -> x + y ;
        System.out.println(fun.apply(2).apply(3));

        Function<Integer , Function<Integer ,Function<Integer , Integer>>> fun2 = x -> y -> z -> x + y + z;
        System.out.println(fun2.apply(2).apply(3).apply(4));

        int[] nums = { 2 , 3 ,4};
        Function f = fun2;
        for (int i = 0 ; i < nums.length; i ++){
            if (f instanceof Function){
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function){
                    f = (Function) obj;
                }else {
                    System.out.println("调用结束：" + obj);
                }
            }
        }
    }
}
