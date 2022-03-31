package com.keshe4ka;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите строку:");
        String userInput = readUserInput();

        // Получение настроек из .yml
        Config config = new Config(args);
        int indexedColumn = config.getIndexedColumn();
        String fileName = config.getFileName();

        long start = System.currentTimeMillis();

        // Получение списка подходящих под поисковый запрос строк
        ArrayList<Airport> airports = searchAirport(indexedColumn, fileName, userInput);

        // Сортировка в лексикографическом порядке
        airports.sort(new AirportSorter(indexedColumn));

        long finish = System.currentTimeMillis();

        for (Airport airport : airports) {
            System.out.println(airport.toString());
        }
        System.out.printf("Найдено строк: %s%nПрошло времени: %s мс %n", airports.size(), finish - start);

    }

    private static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (userInput.isEmpty()) {
            System.out.println("Вы ничего не ввели");
            userInput = scanner.nextLine();
        }
        return userInput.toLowerCase();
    }

    private static ArrayList<Airport> searchAirport(int indexedColumn, String fileName, String userInput) throws IOException {
        ArrayList<Airport> airports = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 0);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[indexedColumn].toLowerCase().startsWith(userInput)) {
                Airport airport = new Airport(nextLine);
                airports.add(airport);
            }
        }
        return airports;
    }
}
