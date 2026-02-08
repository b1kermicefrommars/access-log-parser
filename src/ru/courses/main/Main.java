package ru.courses.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Использование: java Main access.log");
            return;
        }

        String path = args[0];

        int lineCount = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                int length = line.length();

                if (length > 1024) {
                    throw new LogFileException("Строка длиннее 1024 символов на строке " + lineCount);
                }

                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }

            System.out.println("Количество строк: " + lineCount);
            System.out.println("Максимальная длина строки: " + maxLength);
            System.out.println("Минимальная длина строки: " + minLength);

        } catch (IOException | LogFileException ex) {
            ex.printStackTrace();
        }
    }
}