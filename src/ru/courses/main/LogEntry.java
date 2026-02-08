package ru.courses.main;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) \\S+ \\S+ \\[([^\\]]+)\\] \"(\\S+) (\\S+) \\S+\" (\\d+) (\\d+) \"([^\"]*)\" \"([^\"]*)\".*$"
    );

    public LogEntry(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат строки лога");
        }

        // IP адрес
        this.ipAddress = matcher.group(1);

        // Дата и время
        String dateTimeStr = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd/MMM/yyyy:HH:mm:ss Z",
                Locale.ENGLISH
        );
        this.dateTime = ZonedDateTime.parse(dateTimeStr, formatter).toLocalDateTime();

        // Метод и путь
        this.method = HttpMethod.valueOf(matcher.group(3));
        this.path = matcher.group(4);

        // Код ответа и размер
        this.responseCode = Integer.parseInt(matcher.group(5));
        this.responseSize = Integer.parseInt(matcher.group(6));

        // Referer
        this.referer = matcher.group(7);

        // User-Agent
        String userAgentStr = matcher.group(8);
        this.userAgent = new UserAgent(userAgentStr);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
