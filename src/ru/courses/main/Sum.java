package ru.courses.main;

public class Sum {
    public static void main(String[] args) {
        double sum = 0.0;
        for (String arg : args) {
            if (isValidDouble(arg)) {
                sum += Double.parseDouble(arg);
            }
        }
        System.out.println(sum);
    }

    private static boolean isValidDouble(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
