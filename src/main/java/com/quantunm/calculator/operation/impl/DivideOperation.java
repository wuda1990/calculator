package com.quantunm.calculator.operation.impl;

import com.quantunm.calculator.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author: huajun.wu
 * @create: 2020-03-07
 **/
@Service
public class DivideOperation implements Operation {

    @Autowired
    MathContext mc;

    @Override
    public  BigDecimal calculate(BigDecimal firstNum,BigDecimal secondNum){
        return firstNum.divide(secondNum, mc);
    }
}
