package com.quantunm.calculator;

import com.quantunm.calculator.calc.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.MathContext;
import java.util.Scanner;

/**
 * @author: huajun.wu
 * @create: 2020-03-07
 **/
@Slf4j
@SpringBootApplication
public class AppApplication implements CommandLineRunner {
    @Autowired
    Calculator calculator;

    @Bean
    public MathContext getMc() {
        return new MathContext(15);
    }


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            log.info("Please input:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            log.info(calculator.calculate(input));
        }
    }
}

