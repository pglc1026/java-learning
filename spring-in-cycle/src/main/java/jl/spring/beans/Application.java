package jl.spring.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Will Liu
 * @date 2021/8/17
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
