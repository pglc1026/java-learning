package chapter4.thread;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/13
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个 job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     */
    void removeWorkers(int num);

    /**
     * @return 正在执行的任务数量
     */
    int getJobSize();

}
