package ru.academits.ignatkov.temperature.model;

public class KelvinScale implements Scale {
    @Override
    public String toString() {
        return "Кельвин";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature + 273.15;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature - 273.15;
    }
}
