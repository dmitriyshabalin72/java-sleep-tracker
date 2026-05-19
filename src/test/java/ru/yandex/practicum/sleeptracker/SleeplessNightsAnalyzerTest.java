package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleeplessNightsAnalyzer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAnalyzerTest {

    @Test
    void shouldFindSleeplessNight() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        LocalDateTime.of(2025, 10, 1, 10, 0),
                        SleepQuality.NORMAL
                )
        );

        long result = (long) new SleeplessNightsAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(1, result);
    }

    @Test
    void shouldNotFindSleeplessNight() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        SleepQuality.GOOD
                )
        );

        long result = (long) new SleeplessNightsAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(0, result);
    }

    @Test
    void shouldIgnoreDaySleep() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 14, 0),
                        LocalDateTime.of(2025, 10, 1, 16, 0),
                        SleepQuality.NORMAL
                )
        );

        long result = (long) new SleeplessNightsAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(1, result);
    }

    @Test
    void shouldHandleCrossMonth() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 9, 30, 23, 0),
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        SleepQuality.GOOD
                )
        );

        long result = (long) new SleeplessNightsAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(0, result);
    }
}