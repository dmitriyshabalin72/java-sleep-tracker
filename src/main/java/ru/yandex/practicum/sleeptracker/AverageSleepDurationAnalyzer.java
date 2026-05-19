package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AverageSleepDurationAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<Long> analyze(
            List<SleepingSession> sessions) {

        return new SleepAnalysisResult<>(
                "Средняя длительность",
                Math.round(
                        sessions.stream()
                                .mapToLong(
                                        SleepingSession::duration
                                )
                                .average()
                                .orElse(0)
                )
        );
    }

    public static class SessionsCountAnalyzer
            implements SleepAnalyzer {

        @Override
        public SleepAnalysisResult<Long> analyze(
                List<SleepingSession> sessions) {

            return new SleepAnalysisResult<>(
                    "Количество сессий",
                    (long) sessions.size()
            );
        }
    }

    public static class SleepTrackerApp {

        private static final DateTimeFormatter FORMAT =
                DateTimeFormatter.ofPattern(
                        "dd.MM.yy HH:mm"
                );

        private static final List<SleepAnalyzer>
                ANALYZERS = List.of(

                new SessionsCountAnalyzer(),
                new MinSleepDurationAnalyzer(),
                new MaxSleepDurationAnalyzer(),
                new AverageSleepDurationAnalyzer(),
                new BadQualitySleepAnalyzer(),
                new SleeplessNightsAnalyzer(),
                new ChronotypeAnalyzer()
        );

        public static void main(String[] args) {

            if (args.length == 0) {

                System.out.println(
                        "Укажите путь к файлу"
                );

                return;
            }

            try (Stream<String> lines =
                         Files.lines(Path.of(args[0]))) {

                List<SleepingSession> sessions =
                        lines
                                .filter(line ->
                                        !line.isBlank()
                                )
                                .map(
                                        SleepTrackerApp
                                                ::parse
                                )
                                .collect(Collectors.toList());

                ANALYZERS.stream()
                        .map(a ->
                                a.analyze(sessions)
                        )
                        .forEach(System.out::println);

            } catch (IOException e) {

                System.out.println(
                        "Ошибка файла"
                );

            } catch (Exception e) {

                System.out.println(
                        "Ошибка данных"
                );
            }
        }

        private static SleepingSession parse(
                String line) {

            String[] p = line.split(";");

            if (p.length != 3) {
                throw new IllegalArgumentException();
            }

            return new SleepingSession(

                    LocalDateTime.parse(
                            p[0],
                            FORMAT
                    ),

                    LocalDateTime.parse(
                            p[1],
                            FORMAT
                    ),

                    SleepQuality.valueOf(p[2])
            );
        }
    }
}