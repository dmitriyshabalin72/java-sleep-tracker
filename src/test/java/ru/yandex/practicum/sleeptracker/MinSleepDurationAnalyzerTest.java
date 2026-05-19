package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.MinSleepDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSleepDurationAnalyzerTest {

    @Test
    void shouldFindMinDuration() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(4),
                        SleepQuality.GOOD
                ),

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(8),
                        SleepQuality.NORMAL
                )
        );

        long result = (long) new MinSleepDurationAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(240, result);
    }

    @Test
    void shouldReturnZero() {

        long result = (long) new MinSleepDurationAnalyzer()
                .analyze(List.of())
                .getValue();

        assertEquals(0, result);
    }
}