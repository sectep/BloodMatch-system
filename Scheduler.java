package BloodMatch.src.bloodmatch;

import java.util.ArrayList;

// a class, which manages the thread flow.
public class Scheduler {
    static ArrayList<PatientThread> queue = new ArrayList<>();
    static int max = 0;

    // method, which manipulates with a thread, based on priority.
    synchronized static void manageFLow() {
        startThreads();
        maxPriority();
        for (PatientThread thread : queue) {
            if (thread.thrd.getPriority() == max) {
                max = 0;
                thread.requestResume();
                Patient.removePatient(thread.patient);
            }
        }
    }

    // method, which resumes the next suspended thread.
    static void triggerNext() {
        for (PatientThread thread : queue) {
            if (thread.suspended) {
                thread.requestResume();
                return;
            }
        }
    }

    // method, which finds the highest priority on queue array.
    static void maxPriority() {
        for (PatientThread thread : queue) {
            if (thread.thrd.getPriority() > max)
                max = thread.thrd.getPriority();
        }
    }

    // method, which starts all threads.
    static void startThreads() {
        for (PatientThread thread : queue) {
            thread.thrd.start();
            thread.requestSuspend();
        }
    }
}
