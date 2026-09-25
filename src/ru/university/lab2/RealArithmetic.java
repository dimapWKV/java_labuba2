package ru.university.lab2;

public class RealArithmetic {
    public static void runRealArithmetic() {
        compareTheIncomparable();
    }

    private static void doubleSum() {
        System.out.println("1. 0.1 + 0.2");

        double a = 0.1;
        double b = 0.2;
        double sum = a + b;

        System.out.println("0.1 + 0.2 = " + sum);
        //Числа 0.1 и 0.2 не имеют ТОЧНОГО представления в двоичной системе счисления,
        // аналогично тому, как 1/3 не имеет точного представления в десятичной системе.
        // Java хранит ближайшее возможное двоичное приближение к 0.1 и к 0.2.
        // При сложении этих приближений накапливается погрешность, и результат
        // получается не ровно 0.3.
        // Литерал 0.3 в коде тоже хранится как приближение, но другое приближение,
        // не совпадающее с суммой приближений 0.1 и 0.2 - отсюда false при сравнении.
    }

    private static void theCircleAmount() {
        System.out.println("2. 0.1 сложено 10 раз");

        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            sum += 0.1;
        }

        System.out.println("Результат: " + sum);
        System.out.println("sum == 1.0 ? " + (sum == 1.0));
        // Каждое прибавление 0.1 вносит свою маленькую погрешность округления,
        // и эти погрешности накапливаются (аккумулируются) на каждой итерации.
        // Чем больше операций с плавающей точкой выполняется подряд,
        // тем больше копится ошибок округления. - А.В.Медведев
        //из сборника: цитаты легенд
    }

    private static void epsilonPrecision() {
        System.out.println("3. Сравнение double через epsilon");

        double x = 0.1 + 0.2;
        double y = 0.3;

        System.out.println("x == y (обычное сравнение): " + (x == y));
        System.out.println("almostEqual(x, y, 1e-9): " + almostEqual(x, y, 1e-9));

        double sum = 0.0;
        for (int i = 0; i < 10; i++) sum += 0.1;
        System.out.println("almostEqual(sum, 1.0, 1e-9): " + almostEqual(sum, 1.0, 1e-9));
        System.out.println();
    }

    private static boolean almostEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) <= epsilon;
    }

    private static void infinityAndNaN() {
        System.out.println("Infinity и NaN");

        double posInf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;

        System.out.println("1.0 / 0.0  = " + posInf);
        System.out.println("-1.0 / 0.0 = " + negInf);
        System.out.println("0.0 / 0.0  = " + nan);

        System.out.println("Double.isInfinite(posInf): " + Double.isInfinite(posInf));
        System.out.println("Double.isNaN(nan): " + Double.isNaN(nan));

        System.out.println("NaN != NaN: " + (nan != nan));
        System.out.println("NaN == NaN: " + (nan == nan));
    }

    private static void compareTheIncomparable() {
        System.out.println("5. Округление: (int), round, floor, ceil");

        double[] values = {2.7, 2.3, -2.7, -2.3, 2.5, -2.5};

        System.out.printf("%-8s %-12s %-12s %-12s %-12s%n",
                "value", "(int)", "Math.round", "Math.floor", "Math.ceil");

        for (double v : values) {
            System.out.printf("%-8s %-12d %-12d %-12.0f %-12.0f%n",
                    v, (int) v, Math.round(v), Math.floor(v), Math.ceil(v));
        }

        // (int) v просто ОТБРАСЫВАЕТ дробную часть (усечение к нулю),
        //         работает одинаково что для положительных, что для
        //         отрицательных чисел: (int)2.7 = 2, (int)-2.7 = -2
        //
        // Math.round(v) округляет к БЛИЖАЙШЕМУ целому. Для .5 округляет
        //                "вверх" (в сторону +бесконечности), а не по
        //                 "банковскому" округлению:
        //                 Math.round(2.5) = 3, но Math.round(-2.5) = -2 (!),
        //                 т.к. формула round(x) = floor(x + 0.5), а не
        //                  "округление от нуля" - несимметрично для +/- .5
        //
        // Math.floor(v) округляет ВНИЗ (к -бесконечности), результат
        //                всегда <= исходного значения:
        //                 floor(2.7) = 2.0, floor(-2.7) = -3.0
        //
        // Math.ceil(v) округляет ВВЕРХ (к +бесконечности), результат
        //                    всегда >= исходного значения:
        //                    ceil(2.7) = 3.0, ceil(-2.7) = -2.0
        //
        // int и Math.round дают РАЗНЫЙ результат уже на положительных
        // числах с дробной частью >= 0.5 (2.7 -> (int)=2, round=3),
        // а для отрицательных чисел разница ещё заметнее из-за разного
        // направления округления ((int) - к нулю, floor - вниз).
    }

    private static void step6_floatVsDoublePrecision() {
        System.out.println("6. Точность float vs double");

        float fa = 0.1f;
        float fb = 0.2f;
        float fSum = fa + fb;

        double da = 0.1;
        double db = 0.2;
        double dSum = da + db;

        System.out.println("float:  0.1f + 0.2f = " + fSum);
        System.out.println("double: 0.1 + 0.2   = " + dSum);
        // float (32 бита: 1 знаковый, 8 под экспоненту, 23 под мантиссу) хранит
        // числа с гораздо меньшей точностью, чем double (64 бита: 1+11+52).
        // У float примерно 6-7 значащих десятичных цифр точности,
        // у double - примерно 15-16 значащих цифр.
        // Поэтому погрешность у float обычно "виднее" на более коротких числах.

        float bigFloatSum = 0f;
        for (int i = 0; i < 10; i++) bigFloatSum += 0.1f;

        double bigDoubleSum = 0.0;
        for (int i = 0; i < 10; i++) bigDoubleSum += 0.1;

        System.out.println("float:  10x += 0.1f -> " + bigFloatSum);
        System.out.println("double: 10x += 0.1  -> " + bigDoubleSum);
        // Оба результата не равны ровно 1.0, но double обычно ближе к 1.0,
        // чем float, т.к. хранит существенно больше значащих бит мантиссы -
        // погрешность округления при каждом сложении меньше.

        // Демонстрация предела точности float на "длинном" числе:
        float fPrecisionTest = 16777216.0f; // 2^24
        System.out.println("2^24 as float:      " + fPrecisionTest);
        System.out.println("2^24 + 1 as float:  " + (fPrecisionTest + 1f));
        // float не может точно представить 16777217 (2^24 + 1), т.к. 23-битной
        // мантиссы не хватает для этой точности при таком порядке величины -
        // результат "схлопывается" обратно в 16777216.0!
        // double справляется с этим же числом без проблем (у него 52 бита мантиссы):
        double dPrecisionTest = 16777216.0;
        System.out.println("2^24 + 1 as double: " + (dPrecisionTest + 1.0));
        System.out.println();
    }
}
