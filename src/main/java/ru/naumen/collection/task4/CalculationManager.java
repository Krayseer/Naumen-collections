package ru.naumen.collection.task4;

import java.util.LinkedList;
import java.util.Queue;

public class CalculationManager {

    private final Queue<Runnable> tasksQueue = new LinkedList<>();

    private boolean inProcess = false;

    public void addTask(Runnable task) {
        synchronized (tasksQueue) {
            tasksQueue.add(task);
            if (!inProcess) {
                inProcess = true;
                while (!tasksQueue.isEmpty()) {
                    Runnable nextTask = tasksQueue.poll();
                    nextTask.run();
                }
                inProcess = false;
            }
        }
    }

}