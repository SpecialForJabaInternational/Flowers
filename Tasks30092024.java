package com.ydo4ki.jaba_international;

import jdk.nashorn.internal.parser.DateParser;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.ydo4ki.jaba_international.Constants.u;

class Class8 {
    public static void main(String[] args) {
        //long sp = u.allocateMemory(64);
        //u.putInt(sp, 1);
        //u.putInt(sp + 4, 2);
        //u.putInt(sp + 8, 3);
        //u.putInt(sp + 12, 4);
        //u.putInt(sp + 16, 5);
        //dateCheck("30.09.2024");
        System.out.println(mod(4, 0));
    }

    // 1
    static int max(int a, int b) {
        return Integer.compare(a, b) > 0 ? a : b;
    }

    // 2
    static int div(int a, int b) throws ArithmeticException {
        return a / b; // ArithmeticException выбрасывается по умолчанию
    }

    // 3
    static int convert(String a) throws NumberFormatException {
        return Integer.parseInt(a); // NumberFormatException выбрасывается по умолчанию
    }

    // 4
    static void checkAge(int age) throws IllegalArgumentException {
        if (age < 0 || age > 150) throw new IllegalArgumentException();
    }

    // 5
    static double sqrt(double n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException();
        return Math.sqrt(n);
    }

    // 6
    static int factorial(int n) {
        return n < 2 ? n : n * factorial(n - 1);
    }

    // 7
    static void arrayZeroCheck(int[] array) {
        for (int i : array) {
            if (i == 0) throw new IllegalArgumentException("Clear, zero. Zero detected");
        }
    }

    // 8
    static double pow(double n, double x) {
        if (x < 0) throw new IllegalArgumentException();
        return Math.pow(n, x);
    }

    // 9
    static String cut(String str, int amount) {
        if (amount > str.length())
            throw new IllegalArgumentException("amount > str.length (" + amount + " > " + str.length() + ")");
        return str.substring(0, amount);
    }

    // 10
    static <T> int find(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) return i;
        }
        throw new NoSuchElementException("element not found");
    }

    // 11
    static String toBinary(long num) {
        if (num < 0) throw new IllegalArgumentException();
        return Long.toBinaryString(num);
    }

    // 12
    static boolean isDividable(int x, int d) {
        if (d == 0) throw new IllegalArgumentException();
        return x % d == 0;
    }

    // 13
    static <T> T get(List<T> list, int index) {
        // оно обычно и так выбрасывается, но В ТЕОРИИ реализация списка может выбрасывать что-то другое
        // поэтому проверяем вручную
        if (index < 0 || index > list.size()) throw new IndexOutOfBoundsException();
        return list.get(index);
    }

    // 14
    static void checkPass(String password) {
        if (password.length() < 8) throw new WeakPasswordException(password);
    }

    private static class WeakPasswordException extends RuntimeException {
        private final String password;

        public String getPassword() {
            return password;
        }

        private WeakPasswordException(String password) {
            this.password = password;
        }
    }

    // 15
    static void dateCheck(String date) throws DateTimeParseException {
        try {
            new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            throw new DateTimeParseException(e.getMessage(), date, 0);
        }
    }

    // 16
    static String cat(String a, String b) throws NullPointerException {
        return a.concat(Objects.requireNonNull(b));
    }

    // 17
    static int mod(int a, int b) throws ArithmeticException {
        return a % b;
    }

    // 18
    // это то же самое, в чём прикол
    static int sqrt(int x) throws IllegalArgumentException {
        return (int) sqrt((double) x);
    }

    // 19
    static double toF(double C) {
        return (C * 9 / 5) + 32;
    }

    // 20
    static void nullOrEmpty(String _Str) {
        if (_Str.isEmpty()) throw new NullPointerException();
    }
}

class Constants {
    public static final Unsafe u = getU();

    // unsafe не пригодился =<
    public static Unsafe getU() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
