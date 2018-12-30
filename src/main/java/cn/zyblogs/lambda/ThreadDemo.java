package cn.zyblogs.lambda;

/**
 * @Class: ThreadDemo.java
 * @Description: lambda表达式
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        }).start();

        // lambda表达式
        new Thread(() -> System.out.println("ok")).start();
    }
}
