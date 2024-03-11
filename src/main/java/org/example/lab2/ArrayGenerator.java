package org.example.lab2;

import java.util.Random;

public class ArrayGenerator {

    public int[] generateArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(200001) - 100000;
        }
        return array;
    }
}
