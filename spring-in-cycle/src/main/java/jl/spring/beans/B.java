package jl.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Will Liu
 * @date 2021/8/17
 */
@Component("b")
//@DependsOn("c")
public class B {

    @Autowired
    private C c;



    @PostConstruct
    public void init() {
        System.out.println("循环依赖测试B init");
//        System.out.println("循环依赖测试" + c);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("循环依赖测试b destroy");
    }


//    @Async
    public void print() {
        System.out.print("循环依赖测试当前Bean B");
    }

}
