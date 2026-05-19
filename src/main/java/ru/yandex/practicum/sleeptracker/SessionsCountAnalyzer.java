package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SessionsCountAnalyzer implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Количество сессий",
                (long) sessions.size()
        );
    }
}