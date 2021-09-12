package ru.academits.ignatkov.csv;

import java.io.*;

public class MainCsv {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Нужно передать два аргумента:" +
                    " 1 - путь к csv-файлу с таблицей, 2 - путь, куда сохранится итоговый html файл. Сейчас аргументов " + args.length);

            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {

            writer.print("<!DOCTYPE html><html><head><title>СSV</title><meta charset=\"UTF-8\"></head><body><table border=\"2\">");

            int cellsCount = 0;
            boolean isCellCreated = false;

            String line = bufferedReader.readLine();

            while (line != null) {
                int cellsInRow = 0;

                for (int i = 0; i < line.length(); i++) {
                    if (i == 0 && !isCellCreated) {
                        cellsInRow++;
                        writer.print("<tr><td>");
                    }

                    if (i == 0 && isCellCreated) {
                        cellsCount++;
                        writer.print("<br/>");
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"') {
                        writer.print("</td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"' && cellsInRow < cellsCount) {
                        writer.print("</td><td></td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == ',') {
                        writer.print("</td><td></td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && !isCellCreated) {
                        writer.print(line.charAt(i) + "</td></tr>");
                        continue;
                    }

                    if (line.charAt(i) == '"' && !isCellCreated) {
                        isCellCreated = true;
                        continue;
                    }

                    if (i != line.length() - 1 && isCellCreated && line.charAt(i) == '"' && line.charAt(i + 1) != '"') {
                        isCellCreated = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && isCellCreated) {
                        writer.print(',');
                        continue;
                    }

                    if (i != line.length() - 1 && isCellCreated && line.charAt(i) == '"' && line.charAt(i + 1) == '"') {
                        writer.print('"');
                        isCellCreated = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && !isCellCreated) {
                        writer.print("</td><td>");
                        cellsInRow++;
                        continue;
                    }

                    if (line.charAt(i) == '<') {
                        writer.print("&lt;");
                        continue;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&gt;");
                        continue;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp;");
                        continue;
                    }

                    writer.print(line.charAt(i));
                }

                line = bufferedReader.readLine();
            }

            writer.print("</table></body></html>");
        } catch (FileNotFoundException e) {
            System.out.println("Входной csv-файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}