package jl.producerconsumer.common;

/**
 * Model
 *
 * @author liuchang39
 * @date 2019/3/18
 */
public interface Model {

    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();

}
