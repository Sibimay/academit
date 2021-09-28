package ru.academits.ignatkov.model;

public class FahrenheitScale implements Scale{
    @Override
    public String toString() {
        return "Фаренгейт";
    }

    @Override
    public double convertFromCelsius(double inTemperature) {
        return inTemperature * 1.8 + 32;
    }

    @Override
    public double convertToCelsius(double inTemperature) {
        return (inTemperature - 32) / 1.8;
    }
}
