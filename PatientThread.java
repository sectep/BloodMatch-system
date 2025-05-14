package BloodMatch.src.bloodmatch;

// a class, which manipulates with athread.
public class PatientThread implements Runnable {
    Thread thrd;
    String donor, patient;
    boolean suspended;

    // a constructor, which creates a Thread.
    PatientThread(String id, String dId) {
        donor = dId;
        patient = id;
        thrd = new Thread(this, id);
        suspended = false;
    }

    // method, which creates and starts a request thread.
    public static void createThread(String id, String dId, int priority) {
        PatientThread myThrd = new PatientThread(id, dId);
        myThrd.thrd.setPriority(priority);
        Scheduler.queue.add(myThrd);

    }

    // method, which manipulates with requests.
    public void run() {

        // implement the suspantion.
        synchronized (this) {
            while (suspended) {
                try {
                    this.wait();
                } catch (InterruptedException exc) {
                    System.out.println("It seems, exception has been occurred: " + exc);
                }
            }
        }

        System.out.println("\nMaking operation for " + thrd.getName() + "...");
        waitFor(1000);
        System.out.println("Operation for " + thrd.getName() + " is completed.");
        Scheduler.queue.remove(this); // remove this thread from queue.
        Scheduler.triggerNext(); // wake up next thread.
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
    public synchronized void requestSuspend() {
        suspended = true;
    }

    // method, which resumes the thread.
    public synchronized void requestResume() {
        suspended = false;
        notify();
    }
}
