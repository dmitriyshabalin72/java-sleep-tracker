package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.ChronotypeAnalyzer;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChronotypeAnalyzerTest {

    @Test
    void shouldDetectOwl() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 10, 0),
                        SleepQuality.GOOD
                )
        );

        Chronotype result =
                (Chronotype) new ChronotypeAnalyzer()
                        .analyze(sessions)
                        .getValue();

        assertEquals(Chronotype.OWL, result);
    }

    @Test
    void shouldDetectLark() {

        List<SleepingSession> sessions = List.of(

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                )
        );

        Chronotype result =
                (Chronotype) new ChronotypeAnalyzer()
                        .analyze(sessions)
                        .getValue();

        assertEquals(Chronotype.LARK, result);
    }
}