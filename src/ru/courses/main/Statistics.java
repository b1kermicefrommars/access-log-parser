package ru.courses.main;

import java.time.LocalDateTime;
import java.time.Duration;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) {
        // Добавляем трафик
        totalTraffic += entry.getResponseSize();

        // Обновляем minTime
        if (minTime == null || entry.getDateTime().isBefore(minTime)) {
            minTime = entry.getDateTime();
        }

        // Обновляем maxTime
        if (maxTime == null || entry.getDateTime().isAfter(maxTime)) {
            maxTime = entry.getDateTime();
        }
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0;
        }

        // Разница в часах
        Duration duration = Duration.between(minTime, maxTime);
        double hours = duration.toMinutes() / 60.0;

        if (hours == 0) {
            return totalTraffic;
        }

        return totalTraffic / hours;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }
}
