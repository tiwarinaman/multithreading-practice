package thread.creation.example2;

public class Main {
    public static void main(String[] args) {
        NewThread obj = new NewThread();
        obj.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("This is new thread by extending the Thread class");
        }
    }
}
