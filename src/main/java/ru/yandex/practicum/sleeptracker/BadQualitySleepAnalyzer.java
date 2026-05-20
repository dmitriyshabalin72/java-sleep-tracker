package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadQualitySleepAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(
            List<SleepingSession> sessions) {

        return new SleepAnalysisResult<>(
                "Плохие сессии",
                sessions.stream()
                        .filter(s ->
                                s.getQuality()
                                        == SleepQuality.BAD
                        )
                        .count()
        );
    }
}