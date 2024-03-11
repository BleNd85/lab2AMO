package org.example.lab2;

public class BozoNelsonSort {

    public static void bozoNelsonSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;

            // Сортування лівої та правої половин
            bozoNelsonSort(array, low, mid);
            bozoNelsonSort(array, mid + 1, high);

            // Злиття відсортованих частин
            merge(array, low, mid, high);
        }
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        // Створення тимчасових масивів для зберігання лівої та правої половин
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Копіювання даних в тимчасові масиви
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[low + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }
        // Злиття тимчасових масивів

        // Ініціалізація індексів лівого, правого і основного масивів
        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копіювання залишкових елементів leftArray[], якщо такі є
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Копіювання залишкових елементів rightArray[], якщо такі є
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
