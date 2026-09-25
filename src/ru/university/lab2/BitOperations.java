package ru.university.lab2;

public class BitOperations {
    public static void runBitOperations() {
        binaryOperators();
        negativeShiftDemo();
        parityAndPowerDemo();
        countSetBitsDemo();
        swapDemo();
    }

    private static String toBinary8(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            sb.append((n >> i) & 1);
        }
        return sb.toString();
    }

    private static void binaryOperators() {
        System.out.println("1. Базовые побитовые операторы");

        int a = 12;
        int b = 15;

        System.out.println("a & b  = " + toBinary8(a & b));
        System.out.println("a | b  = " + toBinary8(a | b));
        System.out.println("a ^ b  = " + toBinary8(a ^ b));
        System.out.println("~a     = " + ~a);
        System.out.println("a << 2 = " + (a << 2));
        System.out.println("a >> 2 = " + (a >> 2));
        System.out.println("a >>> 2 = " + (a >>> 2));
    }

    private static void negativeShiftDemo() {
        System.out.println("\n2. Разница между >> и >>> на отрицательном числе");

        int n = -8; // 11111111 11111111 11111111 11111000

        System.out.println("n       = " + n);
        System.out.println("n в бинарном виде: " + Integer.toBinaryString(n));

        int arithmeticShift = n >> 2;
        int logicalShift = n >>> 2;

        System.out.println("n >> 2  = " + arithmeticShift
                + "  (бинарно: " + Integer.toBinaryString(arithmeticShift) + ")");
        System.out.println("n >>> 2 = " + logicalShift
                + "  (бинарно: " + Integer.toBinaryString(logicalShift) + ")");

        // >> — арифметический сдвиг вправо, сохраняет знак числа: старший (знаковый) бит
        // копируется в освобождающиеся слева позиции. Для отрицательных чисел слева
        // добавляются ЕДИНИЦЫ, что эквивалентно делению на 2^k с округлением к -∞.
        // >>> — логический (беззнаковый) сдвиг вправо. НЕ сохраняет знак: слева ВСЕГДА
        // добавляются НУЛИ, независимо от исходного знака числа.
        // Для отрицательных чисел результат становится большим положительным числом,
        // так как биты трактуются не как дополнительный код числа со знаком,
        // а как обычная последовательность бит.
    }

    private static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    private static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // каждый раз обнуляем младший установленный бит
            count++;
        }
        return count;
    }

    private static void parityAndPowerDemo() {
        System.out.println("\n3. Проверка чётности и степени двойки");

        int[] numbers = {2, 3, 16, 18, 31, 64, 100};

        for (int num : numbers) {
            System.out.println(num
                    + " -> " + (isEven(num) ? "чётное" : "нечётное")
                    + ", " + (isPowerOfTwo(num) ? "является" : "не является") + " степенью двойки");
        }
    }

    private static void countSetBitsDemo() {
        System.out.println("\n4. Подсчёт количества единичных битов");

        int[] numbers = {7, 12, 255, 1024, 1023};

        for (int num : numbers) {
            System.out.println(num + " (" + Integer.toBinaryString(num) + ") -> "
                    + countSetBits(num) + " единичных бит(а/ов)");
        }
    }

    private static void swapDemo() {
        System.out.println("\n5. Обмен значениями через XOR без временной переменной");

        int x = 5;
        int y = 9;

        System.out.println("До обмена: x = " + x + ", y = " + y);

        x = x ^ y;
        y = x ^ y;
        x = x ^ y;

        System.out.println("После обмена: x = " + x + ", y = " + y);
    }
}
