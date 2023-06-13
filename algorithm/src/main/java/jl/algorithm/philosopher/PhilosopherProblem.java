package jl.algorithm.philosopher;

/**
 * 问题描述：一圆桌前坐着5位哲学家，两个人中间有一只筷子，桌子中央有面条。哲学家思考问题，当饿了的时候拿起左右两只筷子吃饭，
 * 必须拿到两只筷子才能吃饭。上述问题会产生死锁的情况，当5个哲学家都拿起自己右手边的筷子，准备拿左手边的筷子时产生死锁现象。
 * <p>
 * 解决办法：
 * 1、添加一个服务生，只有当经过服务生同意之后才能拿筷子，服务生负责避免死锁发生。
 * 2、每个哲学家必须确定自己左右手的筷子都可用的时候，才能同时拿起两只筷子进餐，吃完之后同时放下两只筷子。
 * 3、规定每个哲学家拿筷子时必须拿序号小的那只，这样最后一位未拿到筷子的哲学家只剩下序号大的那只筷子，
 * 不能拿起，剩下的这只筷子就可以被其他哲学家使用，避免了死锁。这种情况不能很好的利用资源。
 * <p>
 * 代码实现：实现第2种方案
 *
 * @author Liu Chang
 * @date 2020/8/20
 */
public class PhilosopherProblem {

    static class Philosopher extends Thread {

        private String name;

        private Fork fork;

        public Philosopher(String name, Fork fork) {
            super(name);
            this.name = name;
            this.fork = fork;
        }

        @Override
        public void run() {
            while (true) {
                think();
                fork.takeFork();
                eat();
                fork.putFork();
            }
        }

        private void think() {
            System.out.println("I'm thinking, my name is " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void eat() {
            System.out.println("I'm eating, my name is " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    static class Fork {

        /**
         * 5只筷子，初始状态都为未使用
         */
        private boolean[] used = {false, false, false, false, false};

        public synchronized void takeFork() {
            int i = Integer.parseInt(Thread.currentThread().getName());
            // 只有当左右手的筷子都未被使用时，才允许获取筷子，且必须同时获取左右手筷子
            if (used[i] || used[(i + 1) % 5]) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 同时占用两只筷子
            used[i] = true;
            used[(i + 1) % 5] = true;
        }

        /**
         * 同时放下两只筷子
         */
        public synchronized void putFork() {
            int i = Integer.parseInt(Thread.currentThread().getName());
            used[i] = false;
            used[(i + 1) % 5] = false;
            notifyAll();//唤醒其他线程
        }

    }

    public static void main(String[] args) {
        Fork fork = new Fork();
        Philosopher p1 = new Philosopher("0", fork);
        Philosopher p2 = new Philosopher("1", fork);
        Philosopher p3 = new Philosopher("2", fork);
        Philosopher p4 = new Philosopher("3", fork);
        Philosopher p5 = new Philosopher("4", fork);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }


}
