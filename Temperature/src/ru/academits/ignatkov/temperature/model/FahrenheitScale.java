package ru.academits.ignatkov.temperature.model;

public class FahrenheitScale implements Scale {
    @Override
    public String toString() {
        return "Фаренгейт";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature * 1.8 + 32;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return (inputTemperature - 32) / 1.8;
    }
}
