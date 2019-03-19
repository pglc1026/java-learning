package jl.producerconsumer.common;

/**
 * Producer
 *
 * @author liuchang39
 * @date 2019/3/18
 */
public interface Producer {

    void produce() throws InterruptedException;

}
