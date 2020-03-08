package com.quantunm.calculator.calc;

import com.quantunm.calculator.operation.impl.*;
import com.quantunm.calculator.util.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: huajun.wu
 * @create: 2020-03-08
 **/
@Service
@Slf4j
public class RPNCalculator implements Calculator {
    Stack<BigDecimal> stack = new Stack<BigDecimal>();
    LinkedList<Stack<BigDecimal>> stackList = new LinkedList<>();
    @Autowired
    MathContext mc;
    @Autowired
    AddOperation addOperation;
    @Autowired
    SubOperation subOperation;
    @Autowired
    MultiOperation multiOperation;
    @Autowired
    DivideOperation divideOperation;
    @Autowired
    SqrtOperation sqrtOperation;

    public String calculate(String input) {
        String[] expers = input.split(" ");

        for (int i = 0; i < expers.length; i++) {
            if (PatternUtil.isFloatNumber(expers[i])) {
                stack.push(new BigDecimal(expers[i], mc));
                stackList.offer((Stack<BigDecimal>) stack.clone());
                continue;
            }
            if ("undo".equals(expers[i])) {
                stackList.pollLast();
                if (!stackList.isEmpty()) {
                    stack = (Stack<BigDecimal>) stackList.peekLast().clone();
                } else {
                    stack.clear();
                    log.error("no operator you can undo");
                }
                continue;
            }
            if ("+".equals(expers[i])) {
                if (stack.size() < 2) {
                    log.warn("operator + (position:{}) in sufficient parameters",2*i+1);
                    break;
                }
                stack.push(addOperation.calculate(stack.pop(),stack.pop()));
            } else if ("-".equals(expers[i])) {
                if (stack.size() < 2) {
                    log.warn("operator - (position:{}) in sufficient parameters",2*i+1);
                    break;
                }
                BigDecimal num2 = stack.pop();
                BigDecimal num1 = stack.pop();
                stack.push(subOperation.calculate(num1,num2));
            }else if ("*".equals(expers[i])){
                if (stack.size() < 2) {
                    log.warn("operator * (position:{}) in sufficient parameters",2*i+1);
                    break;
                }
                stack.push(multiOperation.calculate(stack.pop(),stack.pop()));
            } else if ("/".equals(expers[i])) {
                if (stack.size() < 2) {
                    log.warn("operator / (position:{}) in sufficient parameters",2*i+1);
                    break;
                }
                BigDecimal num2 = stack.pop();
                BigDecimal num1 = stack.pop();
                stack.push(divideOperation.calculate(num1,num2));
            }else if ("sqrt".equals(expers[i])) {
                if (stack.size() < 1) {
                    log.warn("operator sqrt (position:{}) in sufficient parameters",2*i+1);
                    break;
                }
                stack.push(sqrtOperation.calculate(stack.pop()));
            } else if ("clear".equals(expers[i])) {
                stack.clear();
            } else {
                log.error("illegal input!");
                break;
            }
            stackList.offer((Stack<BigDecimal>) stack.clone());
        }
        return printStack();
    }

    public String printStack() {
        StringBuilder sb = new StringBuilder("stack: ");
        if (!CollectionUtils.isEmpty(stack)) {
            for (BigDecimal number : stack) {
                sb.append(number.setScale(10,BigDecimal.ROUND_FLOOR).stripTrailingZeros().toPlainString()).append(" ");
            }
        }
        return sb.toString();
    }
}
