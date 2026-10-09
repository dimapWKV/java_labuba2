package ru.university.lab2.util;

public class Argumenti {
    public static void runArgumenti() {
        System.out.println("Демонстрация перегрузки метода print\n");

        // 1. Целое число → вызывается print(int)
        print(42);

        // 2. Вещественное число → вызывается print(double)
        print(3.14);

        // 3. Строка → вызывается print(String)
        print("Привет, мир!");

        // 4. Массив целых чисел → вызывается print(int[])
        print(new int[]{10, 20, 30, 40});

        System.out.println("\n--- Неочевидные случаи ---\n");

        // 5. char расширяется до int → вызывается print(int)
        print('A');

        // 6. long расширяется до double → вызывается print(double)
        print(100L);

        // 7. float расширяется до double → вызывается print(double)
        print(2.5f);

        // 8. byte расширяется до int → вызывается print(int)
        byte b = 7;
        print(b);

        // 9. short расширяется до int → вызывается print(int)
        short s = 99;
        print(s);

        System.out.println("\n--- 2. Метод с varargs (сумма) ---");
        System.out.println("Вызов с нулём аргументов: sum() = " + sum());
        System.out.println("Вызов с несколькими аргументами: sum(1, 2, 3, 4) = " + sum(1, 2, 3, 4));
        System.out.println("Вызов с готовым массивом: sum(new int[]{10, 20, 30}) = " + sum(new int[]{10, 20, 30}));

        System.out.println("\n--- 3. Возведение в степень (сравнение реализаций) ---");
        System.out.printf("%-10s | %-10s | %-12s | %-12s | %-12s | %-6s%n",
                "Основание", "Степень", "Рекурсия", "Итерация", "Math.pow", "Совпад.");
        System.out.println("-------------------------------------------------------------------------");

        // Тестовые случаи: включая степень 0, степень 1 и обычные значения
        int[][] testCases = {
                {5, 0},   // Степень 0
                {7, 1},   // Степень 1
                {2, 10},  // Обычное значение
                {3, 4}    // Обычное значение
        };

        for (int[] tc : testCases) {
            double x = tc[0];
            int n = tc[1];

            double resRec = powerRecursive(x, n);
            double resIter = powerIterative(x, n);
            double resMath = Math.pow(x, n);

            // Проверка на полное совпадение результатов
            boolean isMatch = (resRec == resIter) && (resIter == resMath);

            System.out.printf("%-10.0f | %-10d | %-12.0f | %-12.0f | %-12.0f | %-6b%n",
                    x, n, resRec, resIter, resMath, isMatch);
        }
    }

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

    private static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

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


