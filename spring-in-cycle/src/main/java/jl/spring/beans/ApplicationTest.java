package jl.spring.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Will Liu
 * @date 2021/8/17
 */
public class ApplicationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.scan("jl.spring.beans");
        context.register(AppConfiguration.class);
        context.register(B.class);
        context.register(C.class);
        context.refresh();
        B b = context.getBean(B.class);
        System.out.println(b);
    }

}
