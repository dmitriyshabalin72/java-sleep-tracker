package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MinSleepDurationAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(
            List<SleepingSession> sessions) {

        return new SleepAnalysisResult<>(
                "Минимальная длительность",
                sessions.stream()
                        .mapToLong(
                                SleepingSession::duration
                        )
                        .min()
                        .orElse(0)
        );
    }
}