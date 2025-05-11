package BloodMatch.src.bloodmatch;

public class Matcher implements Runnable {
    Thread thrd;
    String name;
    boolean suspended;

    // a constructor, which creates a Thread.
    Matcher(String n) {
        name = n;
        thrd = new Thread(this, name);
        suspended = false;

    }

    // create and start a request thread.
    public static Matcher createAndRun(String n) {
        Matcher myThrd = new Matcher(n);
        myThrd.thrd.start();
        return myThrd;
    }

    // method, which manipulates with requests.
    public void run() {

        // implement the suspantion.
        synchronized (this) {
            while (suspended) {
                try {
                    thrd.wait();
                } catch (InterruptedException exc) {
                    System.out.println("It seems, exception has been occurred: " + exc.getStackTrace());
                }
            }
        }
    }

    // method, which suspends the thread.
    public synchronized void suspend() {
        suspended = true;
    }

    // method, which resumes the thread.
    public synchronized void resume() {
        suspended = false;
        notify();
    }
}
