package ru.academits.ignatkov.hashtable_main;

import ru.academits.ignatkov.hashtable.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<String> hashTable1 = new MyHashTable<>(10);
        hashTable1.add("Ivan");
        hashTable1.add("Vladimir");
        hashTable1.add("Irina");

        System.out.println("Новая хэш-таблица: ");
        System.out.println(hashTable1);
        System.out.println("Её размер: " + hashTable1.size() + ". Она пуста: " + hashTable1.isEmpty());

        hashTable1.remove("Ivan");
        System.out.println("Хэш-таблица после удаления Ivan : ");
        System.out.println(hashTable1);

        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("Anna");
        hashTable2.add("Nikolay");
        hashTable2.add("Darya");

        System.out.println("Еще одна хэш-таблица: ");
        System.out.println(hashTable2);

        hashTable1.addAll(hashTable2);
        System.out.println("После добавления второй хэш-таблицы к первой: ");
        System.out.println(hashTable1);

        System.out.println("Теперь первая таблица содержит значение Anna : " + hashTable1.contains("Anna"));

        hashTable1.retainAll(hashTable2);
        System.out.println("Первая таблица после удаления всех значений, которых нет во второй ");
        System.out.println(hashTable1);

        hashTable1.clear();
        System.out.println("Первая таблица после очистки: ");
        System.out.println(hashTable1);
    }
}
