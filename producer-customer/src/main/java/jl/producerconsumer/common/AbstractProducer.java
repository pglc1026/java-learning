package jl.producerconsumer.common;

/**
 * AbstractProducer
 *
 * @author liuchang39
 * @date 2019/3/18
 */
public abstract class AbstractProducer implements Producer, Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
