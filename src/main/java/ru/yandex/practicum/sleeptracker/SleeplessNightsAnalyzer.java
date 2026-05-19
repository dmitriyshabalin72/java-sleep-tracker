package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public class SleeplessNightsAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(
            List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {

            return new SleepAnalysisResult<>(
                    "Бессонные ночи",
                    0L
            );
        }

        LocalDate first =
                nightDate(
                        sessions.get(0).getStart()
                );

        LocalDate last =
                nightDate(
                        sessions.get(
                                sessions.size() - 1
                        ).getEnd()
                );

        long total =
                ChronoUnit.DAYS.between(
                        first,
                        last
                ) + 1;

        long sleepNights =
                sessions.stream()
                        .flatMap(this::nightStream)
                        .distinct()
                        .count();

        return new SleepAnalysisResult<>(
                "Бессонные ночи",
                total - sleepNights
        );
    }

    private Stream<LocalDate> nightStream(
            SleepingSession s) {

        LocalDateTime start = s.getStart();
        LocalDateTime end = s.getEnd();

        LocalDateTime from =
                start.toLocalDate()
                        .atStartOfDay();

        LocalDateTime to =
                start.toLocalDate()
                        .atTime(6, 0);

        boolean intersects =
                !end.isBefore(from)
                        && !start.isAfter(to);

        if (start.toLocalDate()
                .isBefore(end.toLocalDate())) {

            intersects = true;
        }

        return intersects
                ? Stream.of(nightDate(start))
                : Stream.empty();
    }

    private LocalDate nightDate(
            LocalDateTime dateTime) {

        return dateTime.toLocalTime()
                .isBefore(LocalTime.NOON)

                ? dateTime.toLocalDate()
                .minusDays(1)

                : dateTime.toLocalDate();
    }
}