package ru.university.lab2;

public class BitOperations {
    public static void runBitOperations() {
        binaryOperators();
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

        System.out.println(toBinary8(a & b));
        System.out.println(toBinary8(a | b));
        System.out.println(toBinary8(a ^ b));
        System.out.println(~a);
        System.out.println(a << 2);
        System.out.println(a >> 2);
        System.out.println(a >>> 2);
    }

    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    private static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // снимаем младший установленный бит
            count++;
        }
        return count;
    }
}
