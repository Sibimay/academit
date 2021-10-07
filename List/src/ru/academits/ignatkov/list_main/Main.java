package ru.academits.ignatkov.list_main;

import ru.academits.ignatkov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.addFirst(3);
        singlyLinkedList.add(1, 5);
        singlyLinkedList.add(1, 2);
        singlyLinkedList.delete(3);

        System.out.println("Количество элементов списка: " + singlyLinkedList.getSize() + ". Вот они: " + singlyLinkedList);
        System.out.println("Первый элемент: " + singlyLinkedList.getFirst());
        System.out.println("Второй элемент: " + singlyLinkedList.getData(1));
        Integer previousData = singlyLinkedList.setData(1, 12);
        System.out.println("Теперь второй элемент такой: " + singlyLinkedList.getData(1) + ". Предыдущее значение " + previousData);

        Integer deletedElementData = singlyLinkedList.delete(1);
        System.out.println("После удаления второго элемента список такой: " + singlyLinkedList + ". Удаленный элемент " + deletedElementData);

        System.out.println("После удаления по значению 5. Значение было удалено - " + singlyLinkedList.delete(Integer.valueOf(5)));
        System.out.println("Удалим то же значение ещё раз. Значение было удалено - " + singlyLinkedList.delete(Integer.valueOf(5)));

        Integer deletedFirstElementData = singlyLinkedList.deleteFirst();
        System.out.println("После удаления первого элемента список пуст: " + singlyLinkedList + ". Удаленный элемент " + deletedFirstElementData);

        singlyLinkedList.addFirst(3);
        singlyLinkedList.add(1, 5);
        singlyLinkedList.add(1, 2);

        System.out.println("Новый список: " + singlyLinkedList);
        singlyLinkedList.reverse();
        System.out.println("После разворота список такой: " + singlyLinkedList);

        System.out.println("Скопированный список: " + singlyLinkedList.copy());
    }
}
