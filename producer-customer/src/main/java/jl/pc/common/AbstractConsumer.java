package jl.pc.common;

/**
 * AbstractConsumer
 *
 * @author liuchang39
 * @date 2019/3/18
 */
public abstract class AbstractConsumer implements Consumer, Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
