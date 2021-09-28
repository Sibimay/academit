package ru.academits.ignatkov.model;

public class KelvinScale implements Scale{
    @Override
    public String toString() {
        return "Кельвин";
    }

    @Override
    public double convertFromCelsius(double inTemperature) {
        return inTemperature + 273.15;
    }

    @Override
    public double convertToCelsius(double inTemperature) {
        return inTemperature - 273.15;
    }
}
