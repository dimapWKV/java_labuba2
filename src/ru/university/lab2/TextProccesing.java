package ru.university.lab2;

public class TextProccesing {
    public static void runTextProccesing() {
        /*String test = "Brave new world Привет12";
        int[] counts = countCaracters(test);
        System.out.println("Гласные: " + counts[0]);    // е, и, е, о, е -> 5 (+2 из Hello: e, o)
        System.out.println("Согласные: " + counts[1]);
        System.out.println("Цифры: " + counts[2]);
        System.out.println("Пробелы: " + counts[3]);*/
    }

    private static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        char[] chars = str.toLowerCase().toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(chars[left])) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(chars[right])) {
                right--;
            }

            if (chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private static String turningSentence(String str) {
        char[] chars = str.toCharArray();

        reverse(chars, 0, str.length() - 1);

        int wordStart = 0;
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length ||chars[i] == ' ') {
                reverse(chars, wordStart, i-1);
                wordStart = i + 1;
            }
        }
        return new String(chars);
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    private static int[] countCaracters(String str) {
        int[] count = new int[4];

        if (str == null)
            return count;

        char[] volwes = {'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я',
                        /*Латиница:*/ 'a', 'e', 'i', 'o', 'u', 'y'};

        char[] chars = str.toCharArray();

        for (char c: chars) {
            if (Character.isDigit(c)) {
                count[2]++;
            }
            else if (c == ' ') {
                count[3]++;
            }
            else if (Character.isLetter(c)) {
                char lower = Character.toLowerCase(c);

                if (contains(volwes, lower)) {
                    count[0]++;
                } else {
                    count[1]++;
                }
            }
        }
        return count;
    }

    private static boolean contains(char[] array, char target) {
        for (char c : array) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    private static String encrypt(String text, int k) {
        char[] chars = text.toCharArray();
        int shift = ((k % 26) + 26) % 26;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) ('a' + (c - 'a' + shift) % 26);
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) ('A' + (c - 'A' + shift) % 26);
            }

        }
        return new String(chars);
    }

    private static String decrypt(String text, int k) {
        return encrypt(text, -k);
    }

    public static String longestWord(String s) {
        int bestStart = 0;
        int bestLen = 0;
        int i = 0;
        int n = s.length();

        while (i < n) {
            // пропускаем не-буквы
            while (i < n && !Character.isLetter(s.charAt(i))) {
                i++;
            }
            int start = i;
            // идём до конца слова
            while (i < n && Character.isLetter(s.charAt(i))) {
                i++;
            }
            int len = i - start;
            if (len > bestLen) {
                bestLen = len;
                bestStart = start;
            }
        }
        return s.substring(bestStart, bestStart + bestLen);
    }

}

