package ru.academits.ignatkov.temperature.view;

import ru.academits.ignatkov.temperature.model.Converter;
import ru.academits.ignatkov.temperature.model.Scale;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Locale;

public class ConverterView {
    private JFrame frame;
    private JPanel panel;
    private JTextField entryTemperature;
    private JButton calculateButton;
    private JTextField resultTemperature;
    private JButton clearButton;
    private JComboBox<Scale> inputScales;
    private JComboBox<Scale> outScales;
    private final Converter converter;

    public ConverterView(Converter converter) {
        this.converter = converter;
    }

    private void initFrame() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Перевод температур");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            panel = new JPanel(new GridBagLayout());
            panel.setPreferredSize(new Dimension(300, 250));

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.gridheight = 1;
            c.insets = new Insets(7, 5, 8, 5);
            panel.add(new JLabel("Выберите шкалу для ввода:"), c);

            inputScales = new JComboBox<>(converter.getScales());
            c.gridx = 3;
            c.gridy = 1;
            panel.add(inputScales, c);
            inputScales.setEnabled(true);
            inputScales.setSelectedIndex(0);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(new JLabel("Выберите шкалу для вывода:"), c);

            outScales = new JComboBox<>(converter.getScales());
            c.gridx = 3;
            c.gridy = 2;
            panel.add(outScales, c);
            outScales.setEnabled(true);
            outScales.setSelectedIndex(1);

            c.gridx = 0;
            c.gridy = 3;
            c.insets = new Insets(9, 5, 8, 5);
            panel.add(new JLabel("Введите значение температуры:"), c);

            entryTemperature = new JTextField("", 10);
            c.gridx = 3;
            c.gridy = 3;
            panel.add(entryTemperature, c);

            calculateButton = new JButton("Рассчитать");
            c.gridx = 0;
            c.gridy = 4;
            c.insets = new Insets(7, 80, 8, -25);
            panel.add(calculateButton, c);

            c.gridx = 0;
            c.gridy = 5;
            c.insets = new Insets(7, 5, 8, 5);
            panel.add(new JLabel("Результат:"), c);

            resultTemperature = new JTextField("", 10);
            c.gridx = 3;
            c.gridy = 5;
            resultTemperature.setEditable(false);
            panel.add(resultTemperature, c);

            clearButton = new JButton("Очистить");
            c.gridx = 0;
            c.gridy = 6;
            c.insets = new Insets(7, 80, 8, -25);
            panel.add(clearButton, c);

            initEvents();
        });
    }

    private Scale getInputScale() {
        return (Scale) inputScales.getSelectedItem();
    }

    private Scale getOutScale() {
        return (Scale) outScales.getSelectedItem();
    }

    private double getEntryTemperature() {
        return Double.parseDouble(entryTemperature.getText());
    }

    private void setResultTemperature(double result) {
        resultTemperature.setText(DecimalFormat.getNumberInstance(Locale.ENGLISH).format(result));
    }

    private void getErrorMessage() {
        JOptionPane.showMessageDialog(frame, "Введено не число или неверный формат числа", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }

    private void initEvents() {
        calculateButton.addActionListener(event -> {
            try {
                Scale inputScale = getInputScale();
                Scale outScale = getOutScale();
                double inputTemperature = getEntryTemperature();
                double outTemperature = converter.getResult(inputScale, outScale, inputTemperature);
                setResultTemperature(outTemperature);
            } catch (NumberFormatException e) {
                getErrorMessage();
            }
        });

        clearButton.addActionListener(event -> {
            entryTemperature.setEditable(true);
            entryTemperature.setText("");
            resultTemperature.setText("");
        });
    }

    public void run() {
        initFrame();
    }
}
