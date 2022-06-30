package thread.join.exercise1;

import java.math.BigInteger;

public class ComplexCalculation {

    public static void main(String[] args) {

    }

    public BigInteger calculateResult(BigInteger base1,
                                      BigInteger power1,
                                      BigInteger base2,
                                      BigInteger power2) {
        BigInteger result = BigInteger.ONE;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
