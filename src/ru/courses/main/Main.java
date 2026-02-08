package ru.courses.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "access.log";
        Statistics statistics = new Statistics();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                if (line.length() > 1024) {
                    throw new LogFileException("Строка слишком длинная: " + line.length() + " символов");
                }

                LogEntry entry = new LogEntry(line);
                statistics.addEntry(entry);
            }

            System.out.printf("Средний объём трафика за час: %.2f байт%n", statistics.getTrafficRate());

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (LogFileException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }
}