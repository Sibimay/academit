package ru.academits.ignatkov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("CSV/src/ru/academits/ignatkov/csv/output.html");
             Scanner scanner = new Scanner(new FileInputStream("CSV/src/ru/academits/ignatkov/csv/input.csv"))) {

            writer.print("<!DOCTYPE html><html><head><title>СSV</title><meta charset=\"UTF-8\"></head><body><table border=2>");

            int cellsCount = 0;
            boolean createdCell = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int cellsInRow = 0;

                for (int i = 0; i < line.length(); i++) {
                    if (i == 0 && !createdCell) {
                        cellsInRow++;
                        writer.print("<tr><td>");
                    }

                    if (i == 0 && createdCell) {
                        cellsCount++;
                        writer.print("<br/>");
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"') {
                        writer.print("</td></tr>");
                        createdCell = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"' && cellsInRow < cellsCount) {
                        writer.print("</td><td></td></tr>");
                        createdCell = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == ',') {
                        writer.print("</td><td></td></tr>");
                        createdCell = false;
                        continue;
                    }

                    if (i == line.length() - 1 && !createdCell) {
                        writer.print(line.charAt(i) + "</td></tr>");
                        createdCell = false;
                        continue;
                    }

                    if (line.charAt(i) == '"' && !createdCell) {
                        createdCell = true;
                        continue;
                    }

                    if (i != line.length() - 1 && createdCell && line.charAt(i) == '"' && line.charAt(i + 1) != '"') {
                        createdCell = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && createdCell) {
                        writer.print(',');
                        continue;
                    }

                    if (i != line.length() - 1 && createdCell && line.charAt(i) == '"' && line.charAt(i + 1) == '"') {
                        writer.print('"');
                        createdCell = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && !createdCell) {
                        writer.print("</td><td>");
                        cellsInRow++;
                        createdCell = false;
                        continue;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&lt;");
                        continue;
                    }

                    if (line.charAt(i) == '<') {
                        writer.print("&gt;");
                        continue;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp;");
                        continue;
                    }

                    writer.print(line.charAt(i));
                }
            }

            writer.print("</table></body></html>");
        }
    }
}