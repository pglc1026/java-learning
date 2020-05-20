package jl.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * MyBatisApplication
 *
 * @author Liu Chang
 * @date 2020/5/20
 */
@SpringBootApplication
public class MyBatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyBatisApplication.class, args);
    }

}
