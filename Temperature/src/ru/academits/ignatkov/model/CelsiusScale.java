package ru.academits.ignatkov.model;

public class CelsiusScale implements Scale{
    @Override
    public String toString() {
        return "Цельсий";
    }

    @Override
    public double convertFromCelsius(double inTemperature) {
        return inTemperature;
    }

    @Override
    public double convertToCelsius(double inTemperature) {
        return inTemperature;
    }
}
