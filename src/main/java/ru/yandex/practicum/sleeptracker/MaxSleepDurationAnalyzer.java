package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MaxSleepDurationAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(
            List<SleepingSession> sessions) {

        return new SleepAnalysisResult<>(
                "Максимальная длительность",
                sessions.stream()
                        .mapToLong(
                                SleepingSession::duration
                        )
                        .max()
                        .orElse(0)
        );
    }
}