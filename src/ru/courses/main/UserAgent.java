package ru.courses.main;

public class UserAgent {
    private final String browser;
    private final String operatingSystem;

    public UserAgent(String userAgentString) {
        this.operatingSystem = parseOperatingSystem(userAgentString);
        this.browser = parseBrowser(userAgentString);
    }

    private String parseOperatingSystem(String userAgent) {
        if (userAgent.contains("Windows")) {
            return "Windows";
        } else if (userAgent.contains("Macintosh") || userAgent.contains("Mac OS")) {
            return "macOS";
        } else if (userAgent.contains("Linux")) {
            return "Linux";
        }
        return "Other";
    }

    private String parseBrowser(String userAgent) {
        if (userAgent.contains("Edg")) {
            return "Edge";
        } else if (userAgent.contains("Firefox")) {
            return "Firefox";
        } else if (userAgent.contains("Chrome") && !userAgent.contains("Edg")) {
            return "Chrome";
        } else if (userAgent.contains("Opera") || userAgent.contains("OPR")) {
            return "Opera";
        }
        return "Other";
    }

    public String getBrowser() {
        return browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }
}