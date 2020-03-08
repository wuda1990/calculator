package com.quantunm.calculator.operation;

import com.quantunm.calculator.exception.CalcNotSupportedException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author: huajun.wu
 * @create: 2020-03-07
 **/

public interface  Operation {

    public default BigDecimal calculate(BigDecimal firstNum){
        throw new CalcNotSupportedException("insufficent params");
    }

    public default BigDecimal calculate(BigDecimal firstNum,BigDecimal secondNum){
        throw new CalcNotSupportedException("only need one param");
    }

}
