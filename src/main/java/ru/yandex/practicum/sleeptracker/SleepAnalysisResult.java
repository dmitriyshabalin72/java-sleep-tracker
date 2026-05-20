package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult<T> {

    private final String name;
    private final T value;

    public SleepAnalysisResult(String name,
                               T value) {

        this.name = name;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}