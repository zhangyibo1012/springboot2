package cn.zyblogs.webflux.advice;

import cn.zyblogs.webflux.exception.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @Class: CheckAdvice.java
 * @Description: 异常处理切面
 * @Author ZhangYB
 * @Version V1.0
 */
@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(
            WebExchangeBindException e) {
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleCheckException(
            CheckException e) {
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    private String toStr(CheckException e) {
        return e.getFieldName() + ": 错误的值 " + e.getFieldValue();
    }

    /**
     * 把校验异常转换为字符串
     *
     * @param ex
     * @return
     */
    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
//                数据转换
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
//                reduce多个转为一个 初始值 设置为空字符串 BinaryOperator 二元函数 2个参数
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}
