package thread.join.exercise1;

import java.math.BigInteger;
import java.util.List;

public class ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {

        ComplexCalculation calculation = new ComplexCalculation();
        BigInteger result = calculation.calculateResult(new BigInteger("2"),
                new BigInteger("3"),
                new BigInteger("1"),
                new BigInteger("3"));
        System.out.println(result);

    }

    public BigInteger calculateResult(BigInteger base1,
                                      BigInteger power1,
                                      BigInteger base2,
                                      BigInteger power2) throws InterruptedException {

        List<PowerCalculatingThread> threads = List.of(new PowerCalculatingThread(base1, power1),
                new PowerCalculatingThread(base2, power2));

        threads.forEach(Thread::start);

        for (PowerCalculatingThread thread : threads) {
            thread.join();
        }

        return threads.get(0).getResult().add(threads.get(1).getResult());
    }

    private static class PowerCalculatingThread extends Thread {

        private BigInteger result = BigInteger.ONE;
        private final BigInteger base;
        private final BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
