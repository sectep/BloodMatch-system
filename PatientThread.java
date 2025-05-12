package BloodMatch.src.bloodmatch;

public class PatientThread implements Runnable {
    Thread thrd;
    String name;
    boolean suspended;

    // a constructor, which creates a Thread.
    PatientThread(String id) {
        name = id;
        thrd = new Thread(this, name);
        suspended = false;

    }

    // create and start a request thread.
    public static PatientThread createThread(String id, int priority) {
        PatientThread myThrd = new PatientThread(id);
        myThrd.thrd.setPriority(priority);
        Scheduler.queue.add(myThrd);
        return myThrd;
    }

    // method, which manipulates with requests.
    public void run() {
        Scheduler.manageFLow();

        // implement the suspantion.
        synchronized (this) {
            while (suspended) {
                try {
                    thrd.wait();
                } catch (InterruptedException exc) {
                    System.out.println("It seems, exception has been occurred: " + exc);
                }
            }
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
