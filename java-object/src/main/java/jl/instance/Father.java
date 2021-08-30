package jl.instance;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/8/10
 */
public class Father {


    static {
        System.out.println("父类静态代码块。。。");
    }

    public Father() {
        System.out.println("父类构造方法。。。");
    }

    {
        System.out.println("父类实例代码快。。。");
    }

}
