package com.quantunm.calculator.operation.impl;

import com.quantunm.calculator.operation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author: huajun.wu
 * @create: 2020-03-07
 **/
@Service
@Slf4j
public class SqrtOperation implements Operation {
    @Autowired
    MathContext mc;

    @Override
    public  BigDecimal calculate(BigDecimal firstNum){
        return sqrt(firstNum,mc);
    }

    public static BigDecimal sqrt(BigDecimal value, MathContext mc){
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext sqrtMc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, sqrtMc))).divide(num2, sqrtMc);
            cnt++;
        }
        deviation = deviation.setScale(mc.getPrecision(), mc.getRoundingMode());
        return deviation;
    }


}
