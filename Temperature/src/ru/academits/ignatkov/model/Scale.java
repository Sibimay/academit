package ru.academits.ignatkov.model;

public interface Scale {
    double convertFromCelsius(double inTemperature);

    double convertToCelsius(double inTemperature);
}
