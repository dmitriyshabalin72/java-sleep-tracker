package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.BadQualitySleepAnalyzer;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadQualitySleepAnalyzerTest {

    @Test
    void shouldCountBadSessions() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(5),
                        SleepQuality.BAD
                ),

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(8),
                        SleepQuality.GOOD
                ),

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(6),
                        SleepQuality.BAD
                )
        );

        long result = (long) new BadQualitySleepAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(2, result);
    }

    @Test
    void shouldReturnZero() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(8),
                        SleepQuality.GOOD
                )
        );

        long result = (long) new BadQualitySleepAnalyzer()
                .analyze(sessions)
                .getValue();

        assertEquals(0, result);
    }
}