package ru.academits.ignatkov.temperature.model;

public interface Scale {
    double convertFromCelsius(double inputTemperature);

    double convertToCelsius(double inputTemperature);
}
