package BloodMatch.src.bloodmatch;

import java.util.ArrayList;

public class Scheduler {
    static ArrayList<PatientThread> queue = new ArrayList<>();
    static int max = 0;

    // method, which manipulates with thread on priority.
    static void manageFLow() {
        startThreads();
        maxPriority();
        for (PatientThread thread : queue) {
            if (thread.thrd.getPriority() == max)
                thread.requestResume();
            else {
                thread.requestSuspend();
            }
        }
    }

    // method, which finds the highest priority on threads arraylist.
    static void maxPriority() {
        for (PatientThread thread : queue) {
            if (thread.thrd.getPriority() > max)
                max = thread.thrd.getPriority();
        }
    }

    // method, which starts threads.
    static void startThreads() {
        for (PatientThread thread : queue) {
            thread.thrd.start();
        }
    }
}
