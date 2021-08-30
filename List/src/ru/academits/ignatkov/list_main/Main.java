package ru.academits.ignatkov.list_main;

import ru.academits.ignatkov.list.SinglyLinkedList;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.addFirstElement(3);
        singlyLinkedList.addElementByIndex(1, 5);
        singlyLinkedList.addElementByIndex(1, 2);

        System.out.println("Количество элементов списка: " + singlyLinkedList.getSize() + ". Вот они: " + singlyLinkedList);
        System.out.println("Первый элемент: " + singlyLinkedList.getFirstElement());
        System.out.println("Второй элемент: " + singlyLinkedList.getDataByIndex(1));

        Integer previousData = singlyLinkedList.setElementDataByIndex(1, 12);
        System.out.println("Теперь второй элемент такой: " + singlyLinkedList.getDataByIndex(1) + ". Предыдущее значение " + previousData);

        Integer deletedElementData = singlyLinkedList.deleteElementByIndex(1);
        System.out.println("После удаления второго элемента список такой: " + singlyLinkedList + ". Удаленный элемент " + deletedElementData);

        System.out.println("После удаления по значению 5 список такой. Значение было удалено - " + singlyLinkedList.deleteItemByData(5));
        System.out.println("Удалим то же значение ещё раз. Значение было удалено - " + singlyLinkedList.deleteItemByData(5));

        Integer deletedFirstElementData = singlyLinkedList.deleteFirstElement();
        System.out.println("После удаления первого элемента список пуст: " + singlyLinkedList + ". Удаленный элемент " + deletedFirstElementData);
    }
}
