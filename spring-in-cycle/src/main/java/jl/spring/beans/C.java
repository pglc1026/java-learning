package jl.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Will Liu
 * @date 2021/8/17
 */
@Component("c")
public class C {

    // @Lazy
    @Autowired
    private B b;


    @PostConstruct
    public void init() {
        System.out.println("循环依赖测试C init");
//        System.out.println("循环依赖测试" + b);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("循环依赖测试C destroy");
    }

//    @Async
    public void print() {
        System.out.print("循环依赖测试当前Bean C");
    }

}
