package ru.academits.ignatkov.controller;

import ru.academits.ignatkov.model.Scale;

public class ConverterController {
    public double getResult(Scale inScale, Scale outScale, double inTemperature) {
        double celsiusTemperature = inScale.convertToCelsius(inTemperature);
        return outScale.convertFromCelsius(celsiusTemperature);
    }
}
