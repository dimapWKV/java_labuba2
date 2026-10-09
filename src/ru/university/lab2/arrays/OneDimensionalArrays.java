package ru.university.lab2.arrays;
import java.util.Arrays;

public class OneDimensionalArrays {
    public static void runOneDimensionalArrays() {
        int[] arr = RandomArray(10);
        System.out.println("1. Исходный массив:");

        int min = minimumArr(arr);
        System.out.println("\n2. Минимальный элемент: " + min);

        int max = maximumArr(arr);
        System.out.println("\n2. Минимальный элемент: " + max);

        double avg = averageArr(arr);
        System.out.println("\n2. Минимальный элемент: " + avg);

        System.out.println("\n3. Массив после сортировки (Insertion Sort):");
        System.out.println();
        insertionSort(arr);

        showDiffrence();
    }

    public static int[] RandomArray(int n) {
        int[] arr = new int[n];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        // Вывод массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        return arr;
    }

    private static int minimumArr(int[] arr) {
        int minimum = Integer.MAX_VALUE;

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minimum) {
                minimum = arr[i];
            }
        }
        return minimum;
    }

    private static int maximumArr(int[] arr) {
        int maximum = Integer.MIN_VALUE;

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum;
    }

    private static double averageArr(int[] arr) {
        long sum = 0;

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст");
        }

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (double) sum / arr.length;
    }

    private static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void showDiffrence() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = arr1;

        System.out.println("\n\n1. Оператор '==' (сравнение ссылок):");
        System.out.println("   arr1 == arr2 (разные объекты с одинаковым содержимым): " + (arr1 == arr2));
        System.out.println("   arr1 == arr3 (arr3 — это ссылка на arr1): " + (arr1 == arr3));
        System.out.println("   arr1 == null (существует ли объект): " + (arr1 == null));

        System.out.println("\n2. Метод .equals() (у массивов сравнивает ссылки, а не содержимое):");
        System.out.println("   arr1.equals(arr2) (содержимое одинаковое, но объекты разные): " + arr1.equals(arr2));
        System.out.println("   arr1.equals(arr1) (сравнение объекта с самим собой): " + arr1.equals(arr1));

        System.out.println("\n3. Метод Arrays.equals() (правильное сравнение содержимого):");
        System.out.println("   Arrays.equals(arr1, arr2) (элементы совпадают): " + Arrays.equals(arr1, arr2));
        System.out.println("   Arrays.equals(arr1, arr3) (один и тот же массив): " + Arrays.equals(arr1, arr3));
    }

}


