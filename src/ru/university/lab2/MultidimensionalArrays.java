package ru.university.lab2;

import java.util.Random;

public class MultidimensionalArrays {
    public static void runMultidimensionalArrays() {
        createMatrix(10, 10);
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
