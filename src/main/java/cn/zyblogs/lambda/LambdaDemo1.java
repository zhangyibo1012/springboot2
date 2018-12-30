package cn.zyblogs.lambda;

/**
 * @Class: LambdaDemo1.java
 * @Description: 函数接口 接口中只有一个需要实现的方法 @FunctionalInterface 函数接口注解 代表这个接口只有一个需要实现的方法  尽量把接口拆分的很细 接口可以多集成的 接口有一个默认实现的default方法
 * @Author zyb
 * @Version V1.0
 */
@FunctionalInterface
interface Interface1 {
    int doubleNum(int i);

    default int add(int x, int y) {
        System.out.println("---");
        return x + y;
    }
}

@FunctionalInterface
interface Interface2 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {
    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x, y);
    }
}

public class LambdaDemo1 {
    public static void main(String[] args) {
        Interface1 i1 = (i) -> i * 2;

        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));
        // 最常见的写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;

        Interface1 i4 = i -> {
            System.out.println("---");
            return i * 2;
        };
    }

}
