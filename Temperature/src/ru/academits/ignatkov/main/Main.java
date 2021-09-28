package ru.academits.ignatkov.main;

import ru.academits.ignatkov.model.CelsiusScale;
import ru.academits.ignatkov.model.FahrenheitScale;
import ru.academits.ignatkov.model.KelvinScale;
import ru.academits.ignatkov.model.Scale;
import ru.academits.ignatkov.view.ConverterView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        ConverterView view = new ConverterView(scales);
        view.run();
    }
}
