package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Chronotype> analyze(
            List<SleepingSession> sessions) {

        Map<Chronotype, Long> stats =
                sessions.stream()
                        .filter(this::nightSleep)
                        .collect(Collectors.groupingBy(
                                this::type,
                                Collectors.counting()
                        ));

        long owls =
                stats.getOrDefault(
                        Chronotype.OWL,
                        0L
                );

        long larks =
                stats.getOrDefault(
                        Chronotype.LARK,
                        0L
                );

        long pigeons =
                stats.getOrDefault(
                        Chronotype.PIGEON,
                        0L
                );

        Chronotype result;

        if (owls > larks
                && owls > pigeons) {

            result = Chronotype.OWL;

        } else if (larks > owls
                && larks > pigeons) {

            result = Chronotype.LARK;

        } else {

            result = Chronotype.PIGEON;
        }

        return new SleepAnalysisResult<>(
                "Хронотип",
                result
        );
    }

    private boolean nightSleep(
            SleepingSession s) {

        LocalDateTime start = s.getStart();
        LocalDateTime end = s.getEnd();

        return start.toLocalDate()
                .isBefore(end.toLocalDate())

                || end.toLocalTime()
                .isBefore(LocalTime.of(6, 0));
    }

    private Chronotype type(
            SleepingSession s) {

        LocalTime sleep =
                s.getStart().toLocalTime();

        LocalTime wake =
                s.getEnd().toLocalTime();

        if (sleep.isAfter(
                LocalTime.of(23, 0))
                && !wake.isBefore(
                LocalTime.of(9, 0))) {

            return Chronotype.OWL;
        }

        if (sleep.isBefore(
                LocalTime.of(22, 0))
                && !wake.isAfter(
                LocalTime.of(7, 0))) {

            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }
}