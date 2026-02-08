package ru.courses.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int totalLines = 0;
    private static int yandexBotCount = 0;
    private static int googleBotCount = 0;

    public static void main(String[] args) {
        String fileName = "access.log";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // Проверка длины строки
                if (line.length() > 1024) {
                    throw new LogFileException("Строка слишком длинная: " + line.length() + " символов");
                }

                // Парсинг User-Agent
                parseUserAgent(line);
                totalLines++;
            }

            // Вывод процентов
            printBotPercentages();

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (LogFileException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }

    private static void parseUserAgent(String line) {
        // Находим User-Agent между последними ""
        int lastDoubleQuote = line.lastIndexOf('"');
        int firstDoubleQuote = line.lastIndexOf('"', lastDoubleQuote - 1);

        if (firstDoubleQuote == -1 || firstDoubleQuote >= lastDoubleQuote) {
            return;
        }

        String userAgent = line.substring(firstDoubleQuote + 1, lastDoubleQuote);
        String botName = parseBot(userAgent);

        if ("YandexBot".equals(botName)) {
            yandexBotCount++;
        } else if ("Googlebot".equals(botName)) {
            googleBotCount++;
        }
    }

    private static String parseBot(String userAgent) {
        // Первая скобка ()
        int openParen = userAgent.indexOf('(');
        int closeParen = userAgent.lastIndexOf(')');
        if (openParen == -1 || closeParen <= openParen) {
            return "";
        }

        String firstBrackets = userAgent.substring(openParen + 1, closeParen);

        // Разделяем по ;
        String[] parts = firstBrackets.split(";");
        if (parts.length < 2) {
            return "";
        }

        // Второй элемент, trim
        String fragment = parts[1].trim();

        // До /
        int slashIndex = fragment.indexOf('/');
        if (slashIndex != -1) {
            fragment = fragment.substring(0, slashIndex);
        }

        return fragment;
    }

    private static void printBotPercentages() {
        if (totalLines == 0) {
            System.out.println("Файл пустой");
            return;
        }

        double yandexPercent = (double) yandexBotCount / totalLines * 100;
        double googlePercent = (double) googleBotCount / totalLines * 100;

        System.out.printf("YandexBot: %.2f%%%n", yandexPercent);
        System.out.printf("Googlebot: %.2f%%%n", googlePercent);
    }
}