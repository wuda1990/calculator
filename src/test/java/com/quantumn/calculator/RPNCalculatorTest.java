package com.quantumn.calculator;

import com.quantunm.calculator.AppApplication;
import com.quantunm.calculator.calc.RPNCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: huajun.wu
 * @create: 2020-03-09
 **/
@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest
@ContextConfiguration(classes = AppApplication.class)
public class RPNCalculatorTest {
    @Autowired
    private RPNCalculator calculator;

    @Test
    public void testAdd() {
        String result = calculator.calculate("5 2 +");
        String[] ans = result.split(" ");
        Assert.assertEquals("7",ans[1]);
    }

    @Test
    public void testSqrt() {
        String result = calculator.calculate("2 sqrt");
        String[] ans = result.split(" ");
        Assert.assertEquals("1.4142135623",ans[1]);
    }

    @Test
    public void testSub() {
        String result = calculator.calculate("5 2 -");
        String[] ans = result.split(" ");
        Assert.assertEquals("3",ans[1]);
        result = calculator.calculate("3 -");
        ans = result.split(" ");
        Assert.assertEquals("0",ans[1]);
        result = calculator.calculate("clear");
        ans = result.split(" ");
        Assert.assertEquals(1,ans.length);
    }

    @Test
    public void testUndo() {
        String result = calculator.calculate("5 4 3 2");
        String[] ans = result.split(" ");
        Assert.assertEquals("stack: 5 4 3 2",result.trim());
        result = calculator.calculate("undo undo *");
        ans = result.split(" ");
        Assert.assertEquals("20",ans[1]);
        result = calculator.calculate("5 *");
        ans = result.split(" ");
        Assert.assertEquals("100",ans[1]);
        result = calculator.calculate("undo");
        Assert.assertEquals("stack: 20 5",result.trim());
    }

    @Test
    public void testDivide() {
        String result = calculator.calculate("7 12 2 /");
        String[] ans = result.split(" ");
        Assert.assertEquals("stack: 7 6",result.trim());
        result = calculator.calculate("*");
        ans = result.split(" ");
        Assert.assertEquals("42",ans[1]);
        result = calculator.calculate("4 /");
        ans = result.split(" ");
        Assert.assertEquals("10.5",ans[1]);
    }
}
