package BloodMatch.src.bloodmatch;

public class PatientThread implements Runnable {
    private final Object lock = new Object();
    Thread thrd;
    String donor;
    boolean suspended;

    // a constructor, which creates a Thread.
    PatientThread(String id, String dId) {
        donor = dId;
        thrd = new Thread(this, id);
        suspended = false;
    }

    // create and start a request thread.
    public static PatientThread createThread(String id, String dId, int priority) {
        PatientThread myThrd = new PatientThread(id, dId);
        myThrd.thrd.setPriority(priority);
        Scheduler.queue.add(myThrd);
        return myThrd;
    }

    // method, which manipulates with requests.
    public void run() {

        // implement the suspantion.
        synchronized (lock) {
            while (suspended) {
                try {
                    lock.wait();
                } catch (InterruptedException exc) {
                    System.out.println("It seems, exception has been occurred: " + exc);
                }
            }
        }

        System.out.println("\nMaking operation for " + thrd.getName() + "...");
        waitFor(1000);
        System.out.println("Operation for " + thrd.getName() + " is completed.");
        Scheduler.queue.remove(this);
        Scheduler.triggerNext();
    }

    // method, which waits for a particular time.
    private synchronized void waitFor(int s) {
        try {
            Thread.sleep(s);

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }

    // method, which suspends the thread.
    public void requestSuspend() {
        suspended = true;
    }

    // method, which resumes the thread.
    public void requestResume() {
        suspended = false;
        synchronized (lock) {
            lock.notify();
        }
    }
}
