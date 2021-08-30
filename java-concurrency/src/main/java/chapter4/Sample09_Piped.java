package chapter4;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/13
 */
public class Sample09_Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int received = 0;
        try {
            while ((received = System.in.read()) != -1) {
                out.write(received);
            }
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int received = 0;
            try {
                while ((received = in.read()) != -1) {
                    System.out.println((char) received);
                }
            } catch (Exception e) {
            }
        }
    }

}
