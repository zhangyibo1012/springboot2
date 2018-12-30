package cn.zyblogs.webflux.util;

import cn.zyblogs.webflux.exception.CheckException;

import java.util.stream.Stream;

/**
 * @Class: CheckUtil.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES = { "admin", "guanliyuan" };

    /**
     * 校验名字, 不成功抛出校验异常
     *
     * @param name
     */
    public static void checkName(String value) {
//        of 相当于new ifPresent 如果存在 抛出一个异常
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new CheckException("name", value);
        });
    }
}
