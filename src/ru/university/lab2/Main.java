package ru.university.lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                     _        _     ____   _   _   ____     _      ____  
                    | |      / \\   | __ ) | | | | | __ )   / \\    |___ \\ 
                    | |     / _ \\  |  _ \\ | | | | |  _ \\  / _ \\     __) |
                    | |___ / ___ \\ | |_) || |_| | | |_) |/ ___ \\   / __/ 
                    |_____/_/   \\_\\|____/  \\___/  |____//_/   \\_\\ |_____|
                    
                                        DIMO0000N PRESENTS
                    
                    ===== МЕНЮ ВЫБОРА ЗАДАНИЯ =====
                    1 - Задание 1 (IntegerTraps)
                    2 - Задание 2 (RealArithmetic)
                    3 - Задание 3 (BitOperations)
                    4 - Задание 4 (TextProccesing)
                    5 - Задание 5 (OneDimensionalArrays)
                    6 - Задание 6 (MultidimensionalArrays)
                    7 - Задание 7 (Argumenti)
                    
                    0 - Выход
                    ===============================
                    """);

            System.out.print("Ваш выбор: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Недопустимое значение");
                scanner.next();
                choice = -1;
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> IntegerTraps.runIntegerTraps();
                case 2 -> RealArithmetic.runRealArithmetic();
                case 3 -> BitOperations.runBitOperations();
                case 4 -> TextProccesing.runTextProccesing();
                case 5 -> OneDimensionalArrays.runOneDimensionalArrays();
                case 6 -> MultidimensionalArrays.runMultidimensionalArrays();
                case 7 -> Argumenti.runArgumenti();
                case 0 -> System.out.println("До свидания");
            }
        } while (choice != 0);

        scanner.close();
    }
}

//x & (x - 1)