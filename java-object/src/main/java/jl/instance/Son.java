package jl.instance;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/8/10
 */
public class Son extends Father {

    static {
        System.out.println("子类静态代码块。。。");
    }

    public Son() {
        System.out.println("子类构造方法。。。");
    }

    {
        System.out.println("子类实例代码快。。。");
    }

}
