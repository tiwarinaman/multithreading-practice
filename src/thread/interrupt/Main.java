package thread.interrupt;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(new BlockingTask());

        thread.start();

        thread.interrupt();

        Thread longComputationThread = new Thread(
                new LongComputationTask(new BigInteger("20000"),
                        new BigInteger("100000000")));

        longComputationThread.setDaemon(true);
        longComputationThread.start();
        // here this interrupt method will not be enough to
        // stop the thread
        longComputationThread.interrupt();

    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        private LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power +
                    " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                // need to add this if block, if you want to work
                // the Thread.interrupted() method.
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

}
