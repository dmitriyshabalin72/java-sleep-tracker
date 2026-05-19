package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.MaxSleepDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSleepDurationAnalyzerTest {

    @Test
    void shouldFindMaxDuration() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(6),
                        SleepQuality.NORMAL
                ),

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(9),
                        SleepQuality.GOOD
                )
        );

        long result = (long) new MaxSleepDurationAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(540, result);
    }

    @Test
    void shouldReturnZero() {

        long result = (long) new MaxSleepDurationAnalyzer()
                .analyze(List.of())
                .getValue();

        assertEquals(0, result);
    }
}