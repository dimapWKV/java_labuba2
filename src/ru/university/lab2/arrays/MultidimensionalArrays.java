package ru.university.lab2.arrays;

import java.util.Random;
import java.util.Scanner;

public class MultidimensionalArrays {
    public static void runMultidimensionalArrays() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размеры первой матрицы:");
        System.out.print("Количество строк: ");
        int rows1 = scanner.nextInt();
        System.out.print("Количество столбцов: ");
        int cols1 = scanner.nextInt();

        int[][] mx = createMatrix(rows1, cols1);
        print(mx);
        int[][] tmx = transpose(mx);
        print(tmx);

        System.out.println("\nВведите размеры второй матрицы:");
        System.out.print("Количество строк: ");
        int rows2 = scanner.nextInt();
        System.out.print("Количество столбцов: ");
        int cols2 = scanner.nextInt();

        int[][] ms = createMatrix(rows2, cols2);
        int[][] mmx = multiply(mx,ms);
        print(mmx);
    }

    private static int[][] createMatrix(int m, int n) {
        Random rnd = new Random();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = rnd.nextInt(31) - 10; // числа от -10 до 20
            }
        }
        return a;
    }

    private static void print(int[][] a) {
        if (a == null|| a.length == 0) {
            System.out.println("Пустая матрица");
            return;
        }

        int width = 0;
        for (int[] row: a) {
            for (int v: row) {
                width = Math.max(width, String.valueOf(v).length());
            }
        }

        String formart = "%" + width + "d";
        for (int[] row: a) {
            StringBuffer sb = new StringBuffer("| ");
            for (int j = 0; j < a.length; j++) {
                sb.append(String.format(formart, row[j]));
                sb.append(j < a.length-1 ? " ": " |");
            }

            System.out.println(sb);
        }
        System.out.println();
    }

    private static int[][] transpose(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] t = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = a[i][j];
            }
        }
        return t;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int bRows = b.length;
        int p = b[0].length;

        if (n != bRows) {
            System.out.println("Ошибка: умножение невозможно. Число столбцов первой матрицы ("
                    + n + ") не равно числу строк второй (" + bRows + ").");
            return null;
        }

        int[][] c = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }
}
