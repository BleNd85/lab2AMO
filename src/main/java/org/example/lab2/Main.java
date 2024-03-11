package org.example.lab2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayGenerator arrayGenerator = new ArrayGenerator();
        double startTime = System.currentTimeMillis();
        int[] array = arrayGenerator.generateArray(100);
        System.out.println(Arrays.toString(array));
        BozoNelsonSort.bozoNelsonSort(array, 0, array.length - 1);
        double endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println((endTime - startTime) / 1000 + " с.");
    }
}
