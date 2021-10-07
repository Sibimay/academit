package ru.academits.ignatkov.temperature.model;

public class CelsiusScale implements Scale {
    @Override
    public String toString() {
        return "Цельсий";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature;
    }
}
