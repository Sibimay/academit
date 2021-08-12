package ru.academits.ignatkov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("CSV/src/ru/academits/ignatkov/csv/output.html");
             Scanner scanner = new Scanner(new FileInputStream("CSV/src/ru/academits/ignatkov/csv/input.csv"))) {

            writer.print("<!DOCTYPE html><html><head><title>Файл СSV</title><meta charset=\"UTF-8\"></head><body><table border=\"2\">");
            boolean wasQuotes = false;
            boolean lineStarted = false;
            int cellsCount = 0;
            ArrayList<String> lineParts = new ArrayList<>();

            while (scanner.hasNextLine()) {
                if (cellsCount == 3) {
                    lineParts.add("</tr>");
                    lineParts.add("<tr>");
                }

                String line = scanner.nextLine();

                int cellSeparatorIndex = 0;
                int quotesIndex = 0;
                int startIndex = 0;

                while (cellSeparatorIndex != -1) {
//                    line = line.substring(startIndex);
                    quotesIndex = line.indexOf('"', cellSeparatorIndex);
                    cellSeparatorIndex = line.indexOf(",", startIndex);

                    if (!lineStarted) {
                        lineParts.add("<tr>");
                        lineStarted = true;
                    }

                    if (cellSeparatorIndex != - 1 && cellSeparatorIndex < quotesIndex && !wasQuotes) {
                            lineParts.add("<td>");
                            lineParts.add(line.substring(startIndex, cellSeparatorIndex));
                            lineParts.add("</td>");

                            cellsCount++;
                            startIndex = cellSeparatorIndex + 1;
                    } else if (wasQuotes) {
                        lineParts.add(line.substring(startIndex, quotesIndex));
                        lineParts.add("</td>");
                        startIndex = cellSeparatorIndex + 1;
                    }

                    if (cellSeparatorIndex == -1 && quotesIndex != -1) {
                        int secondQuotesIndex = line.indexOf('"', quotesIndex + 1);

                        if (secondQuotesIndex == - 1) {
                            lineParts.add("<td>");
                            lineParts.add(line.substring(quotesIndex));
                            lineParts.add("<br/>");
                            cellsCount++;
                            wasQuotes = true;
                        }
                    }
                }

                System.out.println(lineParts);
            }

            for (String line : lineParts) {
                writer.print(line);
            }

            writer.print("</table></body></html>");
        }
    }
}
