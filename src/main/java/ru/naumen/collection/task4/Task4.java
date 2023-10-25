package ru.naumen.collection.task4;

import java.util.*;

public class Task4 {

    public static void main(String[] args) {
        CalculationManager manager = new CalculationManager();
        CalculationResultListener listener = result -> System.out.println("Calculation result: " + result);

        manager.addTask(() -> {
            String result = String.format("random integer = %d", new Random().nextInt());
            listener.onCalculationComplete(result);
        });

        manager.addTask(() -> {
            String result = String.format("random string = %s", UUID.randomUUID());
            listener.onCalculationComplete(result);
        });

        manager.addTask(() -> {
            List<Integer> values = List.of(1, 16, 22, 2);
            String result = String.format("max value in list = %d", Collections.max(values));
            listener.onCalculationComplete(result);
        });
    }

}
