package ru.academits.ignatkov.temperature_main;

import ru.academits.ignatkov.temperature.model.*;
import ru.academits.ignatkov.temperature.view.ConverterView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        ConverterView view = new ConverterView(new Converter(scales));
        view.run();
    }
}
