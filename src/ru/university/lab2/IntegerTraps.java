package ru.university.lab2;

public class IntegerTraps {
    public static void runIntegerTraps() {
        step1_minMaxValues();
        step2_overflowMaxPlusOne();
        step3_multiplicationOverflow();
        step4_divisionAndModulo();
        step5_longToIntNarrowing();
        step6_charArithmetic();
        step7_overflowSafeAdd();
    }

    private static void step1_minMaxValues() {
        System.out.println(" 1. MIN/MAX значения");
        System.out.println("byte:  " + Byte.MIN_VALUE + " - " + Byte.MAX_VALUE);
        // byte занимает 1 байт (8 бит), знаковый -> диапазон -128..127 (2^8 значений)
        System.out.println("short: " + Short.MIN_VALUE + " - " + Short.MAX_VALUE);
        // short занимает 2 байта (16 бит) -> -32768..32767
        System.out.println("int:   " + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE);
        // int занимает 4 байта (32 бита) -> -2147483648..2147483647
        System.out.println("long:  " + Long.MIN_VALUE + " - " + Long.MAX_VALUE);
        // long занимает 8 байт (64 бита) -> -9223372036854775808..9223372036854775807

    }

    private static void step2_overflowMaxPlusOne() {
        System.out.println();
        System.out.println(" 2. Integer.MAX_VALUE + 1");
        int max = Integer.MAX_VALUE;
        int result = max + 1;
        System.out.println("Integer.MAX_VALUE = " + max);
        System.out.println("Integer.MAX_VALUE + 1 = " + result);
        // В дополнительном коде старший бит 0111...1 (MAX_VALUE) при +1 переходит в 1000...0,
        // что интерпретируется как минимальное отрицательное число.

    }

    private static void step3_multiplicationOverflow() {
        System.out.println();
        System.out.println(" 3. Integer.MAX_VALUE * 2");

        int resultInt = Integer.MAX_VALUE * 2;
        System.out.println("В типе int:  " + resultInt);
        // Результат: -2. Умножение выполняется в int, переполнение по модулю 2^32,
        // происходит "тихий" wrap-around без исключений.

        long resultLong = (long) Integer.MAX_VALUE * 2;
        System.out.println("В типе long: " + resultLong);
        // Результат: 4294967294, т.к. мы приводим один из операндов к long до
        // умножения, поэтому вся операция выполняется в 64-битной арифметике,
        // переполнения не происходит, т.к. диапазон long значительно больше.

        long wrongCast = Integer.MAX_VALUE * 2;
        System.out.println("Ловушка (умножение в int, потом каст в long): " + wrongCast);
        // Результат: -2. Компилятор сначала вычисляет Integer.MAX_VALUE * 2 в int, поэтому
        // переполнение уже произошло, а затем уже расширение до long.

    }


    private static void step4_divisionAndModulo() {
        System.out.println();
        System.out.println(" 4. Деление и остаток");

        System.out.println("5 / 2   = " + (5 / 2));
        // 2 - целочисленное деление отбрасывает дробную часть (округление к 0)

        System.out.println("-5 / 2  = " + (-5 / 2));
        // -2 - в Java деление округляется к 0,
        // -5/2 = -2.5 -> округление к 0 даёт -2

        System.out.println("5 % 2   = " + (5 % 2));
        // 1 - обычный остаток от деления

        System.out.println("-5 % 2  = " + (-5 % 2));
        // -1 - знак остатка в Java всегда совпадает со знаком делимого (дивиденда),
        // а не делителя. Это следует из тождества: a = (a / b) * b + (a % b)

    }

    private static void step5_longToIntNarrowing() {
        System.out.println();
        System.out.println(" 5. Приведение long -> int");

        long bigValue = (long) Integer.MAX_VALUE + 100;
        System.out.println("long значение: " + bigValue);

        int narrowed = (int) bigValue;
        System.out.println("После (int) приведения: " + narrowed);
        // При сужающем приведении long -> int просто отбрасываются старшие 32 бита
        // 64-битного представления, оставшиеся 32 бита интерпретируются как int, включая знаковый бит

        long veryBig = 10_000_000_000L;
        System.out.println("Ещё пример: " + veryBig + " -> " + (int) veryBig);

    }

    // 6. Арифметика над char
    private static void step6_charArithmetic() {
        System.out.println(" 6. Арифметика над char");

        char letter = 'a';
        char next = (char) (letter + 1);
        System.out.println("'a' + 1 = " + next);
        // char хранит числовой код символа (UTF-16 code unit). letter + 1 в выражении
        // автоматически повышается (promotion) до int, поэтому результат нужно явно
        // привести обратно к char, иначе будет ошибка компиляции.

        char c1 = 'A';
        char c2 = 'B';
        int sumAsNumber = c1 + c2;
        System.out.println("'A' + 'B' как число: " + sumAsNumber);
        // 131 - при сложении двух char они оба повышаются до int,
        // результат - это сумма их числовых кодов, а не "объединение символов"


        char sumAsChar = (char) sumAsNumber;
        System.out.println("Тот же результат (131), приведённый обратно к char: " + sumAsChar);
        // Символ с кодом 131 - это непечатаемый управляющий символ или спецсимвол, т.к. 131 не соответствует привычной букве.
        // Демонстрирует, что char - это просто 16-битное беззнаковое число (0..65535),
        // и арифметика с ним имеет смысл только если результат осмыслен как код символа.

    }


    private static void step7_overflowSafeAdd() {
        System.out.println();
        System.out.println(" 7. Проверка переполнения при сложении int");

        printAddResult(1_000_000_000, 1_000_000_000);
        printAddResult(Integer.MAX_VALUE, 1);
        printAddResult(Integer.MIN_VALUE, -1);
    }

    private static void printAddResult(int a, int b) {
        System.out.println();
        AddResult r = safeAdd(a, b);
        System.out.println(a + " + " + b + " = " + r.sum +
                ", переполнение: " + r.overflow);

        /*
         Складывает два int и определяет, произошло ли переполнение.
         Переполнение при сложении двух чисел одного знака
         возможно только если результат получил чужой знак.
         Если знаки операндов разные - переполнение невозможно,
         результат по модулю всегда меньше большего из операндов.
         */

    }


    private static AddResult safeAdd(int a, int b) {
        int sum = a + b;

        boolean overflow = ((a > 0 && b > 0) && sum < 0)
                || ((a < 0 && b < 0) && sum >= 0);
        return new AddResult(sum, overflow);
    }

    private record AddResult(int sum, boolean overflow) {}
}

