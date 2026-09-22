package ru.university.lab2;

public class Argumenti {
    public static void runArgumenti() {}

    private static void print(int value) {
        System.out.println("print(int): " + value);
    }

    private static void print(double value) {
        System.out.println("print(double): " + value);
    }

    private static void print(String value) {
        System.out.println("print(String): " + value);
    }

    private static void print(int[] value) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i < value.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println("print(int[]): " + sb);
    }

    // ===== 2. Varargs: сумма произвольного количества аргументов =====

    private static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // ===== 3. Возведение в степень: рекурсивно и итеративно =====

    // Рекурсивно: x^n = x * x^(n-1), базовый случай x^0 = 1
    private static double powerRecursive(double x, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Степень должна быть неотрицательной");
        }
        if (n == 0) {
            return 1.0;
        }
        return x * powerRecursive(x, n - 1);
    }

    // Итеративно: обычный цикл-умножение
    private static double powerIterative(double x, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Степень должна быть неотрицательной");
        }
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }
}
