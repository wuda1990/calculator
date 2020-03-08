package com.quantunm.calculator.exception;

/**
 * @author: huajun.wu
 * @create: 2020-03-08
 **/
public class CalcNotSupportedException extends RuntimeException {
    public CalcNotSupportedException(String msg) {
        super(msg);
    }
}
