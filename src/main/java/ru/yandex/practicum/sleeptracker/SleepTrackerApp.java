package ru.yandex.practicum.sleeptracker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final DateTimeFormatter F =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static void main(String[] args) {

        try {

            List<SleepingSession> sessions =
                    Files.lines(Path.of(args[0]))
                            .map(SleepTrackerApp::parse)
                            .collect(Collectors.toList());

            List<SleepAnalyzer> analyzers = List.of(
                    new SessionsCountAnalyzer(),
                    new MinSleepDurationAnalyzer(),
                    new MaxSleepDurationAnalyzer(),
                    new AverageSleepDurationAnalyzer(),
                    new BadQualitySleepAnalyzer(),
                    new SleeplessNightsAnalyzer(),
                    new ChronotypeAnalyzer()
            );

            for (SleepAnalyzer a : analyzers) {
                System.out.println(a.analyze(sessions));
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static SleepingSession parse(String line) {

        String[] p = line.split(";");

        return new SleepingSession(
                LocalDateTime.parse(p[0], F),
                LocalDateTime.parse(p[1], F),
                SleepQuality.valueOf(p[2])
        );
    }
}