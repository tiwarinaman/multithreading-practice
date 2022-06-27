package thread.creation.example1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        threadCreationExample1();
        threadCreationExample2();
    }

    static void threadCreationExample2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code will run in a new thread
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving Thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " +
                        t.getName() + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }

    static void threadCreationExample1() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code will run in new thread
                System.out.println("We are in new thread : " + Thread.currentThread().getName());
                System.out.println("Current thread priority is : " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread : " +
                Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread : " +
                Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(1000);
    }
}
