package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.AverageSleepDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionsCountAnalyzerTest {

    @Test
    void shouldCountSessions() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(8),
                        SleepQuality.GOOD
                ),

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(5),
                        SleepQuality.BAD
                )
        );

        long result = (long) new AverageSleepDurationAnalyzer.SessionsCountAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(2, result);
    }

    @Test
    void shouldReturnZero() {

        long result = (long) new AverageSleepDurationAnalyzer.SessionsCountAnalyzer()
                .analyze(List.of())
                .getValue();

        assertEquals(0, result);
    }
}