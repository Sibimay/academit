package ru.academits.ignatkov.lambda_main;

import ru.academits.ignatkov.lambda.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Ivan", 42),
                new Person("Anna", 12),
                new Person("Vladimir", 7),
                new Person("Nikolay", 64),
                new Person("Darya", 23),
                new Person("Vladimir", 32),
                new Person("Ivan", 10),
                new Person("Anna", 25)
        ));

        List<String> distinctNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Список уникальных имен: " + distinctNames);

        String distinctNamesString = distinctNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(distinctNamesString);

        List<Person> personsUnder18 = persons
                .stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());
        Double personsUnder18AverageAge = personsUnder18
                .stream()
                .collect(averagingDouble(Person::getAge));
        System.out.println("Средний возраст до 18 лет: " + personsUnder18AverageAge);

        Map<String, Double> averageAgesByNames = persons
                .stream()
                .collect(Collectors.groupingBy(Person::getName, averagingDouble(Person::getAge)));
        averageAgesByNames.forEach((k, v) -> System.out.println("Средний возраст: " + k + " - " + v));

        List<Person> personsByAgeFrom20To45 = persons
                .stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> (p2.getAge() - p1.getAge()))
                .collect(Collectors.toList());

        System.out.println(personsByAgeFrom20To45);
    }
}