package ru.academits.ignatkov.view;

import ru.academits.ignatkov.controller.ConverterController;
import ru.academits.ignatkov.model.Scale;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Locale;

public class ConverterView {
    private final JFrame frame = new JFrame("Перевод температур");
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JTextField entryTemperature = new JTextField("", 10);
    private final JButton calculateButton = new JButton("Рассчитать");
    private final JTextField resultTemperature = new JTextField("", 10);
    private final JButton clearButton = new JButton("Очистить");
    private final Scale[] scales;
    private JComboBox<Scale> inScales;
    private JComboBox<Scale> outScales;

    public ConverterView(Scale[] scales) {
        this.scales = scales;
    }

    private void initFrame() {
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

            inScales = new JComboBox<>(scales);
            c.gridx = 3;
            c.gridy = 1;
            panel.add(inScales, c);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(new JLabel("Выберите шкалу для вывода:"), c);

            outScales = new JComboBox<>(scales);
            c.gridx = 3;
            c.gridy = 2;
            panel.add(outScales, c);

            c.gridx = 0;
            c.gridy = 3;
            c.insets = new Insets(9, 5, 8, 5);
            panel.add(new JLabel("Введите значение температуры:"), c);

            c.gridx = 3;
            c.gridy = 3;
            panel.add(entryTemperature, c);

            c.gridx = 0;
            c.gridy = 4;
            c.insets = new Insets(7, 80, 8, -25);
            panel.add(calculateButton, c);

            c.gridx = 0;
            c.gridy = 5;
            c.insets = new Insets(7, 5, 8, 5);
            panel.add(new JLabel("Результат:"), c);

            c.gridx = 3;
            c.gridy = 5;
            resultTemperature.setEditable(false);
            panel.add(resultTemperature, c);

            c.gridx = 0;
            c.gridy = 6;
            c.insets = new Insets(7, 80, 8, -25);
            panel.add(clearButton, c);
        });
    }

    private Scale getInScale() {
        return (Scale) inScales.getSelectedItem();
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
                Scale inScale = getInScale();
                Scale outScale = getOutScale();
                double inTemperature = getEntryTemperature();
                double outTemperature = new ConverterController().getResult(inScale, outScale, inTemperature);
                setResultTemperature(outTemperature);
                inScales.setEnabled(false);
                outScales.setEnabled(false);
                entryTemperature.setEditable(false);
            } catch (NumberFormatException e) {
                getErrorMessage();
            }
        });

        clearButton.addActionListener(event -> {
            inScales.setEnabled(true);
            inScales.setSelectedIndex(0);
            outScales.setEnabled(true);
            outScales.setSelectedIndex(0);
            entryTemperature.setEditable(true);
            entryTemperature.setText("");
            resultTemperature.setText("");
        });
    }

    public void run() {
        initFrame();
        initEvents();
    }
}
