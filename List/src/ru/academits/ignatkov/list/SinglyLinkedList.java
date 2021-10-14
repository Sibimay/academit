package ru.academits.ignatkov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        checkListEmpty();

        return head.getData();
    }

    public E getData(int index) {
        return getItem(index).getData();
    }

    public E setData(int index, E data) {
        ListItem<E> item = getItem(index);
        E oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public E delete(int index) {
        checkIndex(index, size);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previous = getItem(index - 1);
        E deletedData = previous.getNext().getData();
        previous.setNext(previous.getNext().getNext());
        size--;

        return deletedData;
    }

    private ListItem<E> getItem(int index) {
        checkIndex(index, size);

        ListItem<E> item = head;
        int i = 0;

        while (i != index) {
            item = item.getNext();
            i++;
        }

        return item;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        checkIndex(index, size + 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        size++;
    }

    public boolean delete(E data) {
        for (ListItem<E> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(current.getData(), data)) {
                if (previous != null) {
                    previous.setNext(previous.getNext().getNext());
                } else {
                    head = head.getNext();
                }

                size--;

                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        checkListEmpty();

        E deletedData = head.getData();

        head = head.getNext();
        size--;

        return deletedData;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        for (ListItem<E> current = head.getNext(), previous = head, beforePrevious = null;
             current != null; previous = current, current = current.getNext()) {
            previous.setNext(beforePrevious);
            beforePrevious = previous;

            if (current.getNext() == null) {
                head = current;
                head.setNext(previous);
                return;
            }
        }
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        if (head == null) {
            return copiedList;
        }

        ListItem<E> copiedListItem = new ListItem<>(head.getData());

        copiedList.head = copiedListItem;
        ListItem<E> item = head.getNext();

        while (item != null) {
            ListItem<E> nextCopiedListItem = new ListItem<>(item.getData());
            copiedListItem.setNext(nextCopiedListItem);
            copiedListItem = nextCopiedListItem;

            item = item.getNext();
        }

        copiedList.size = size;

        return copiedList;
    }

    private static void checkIndex(int index, int resultSize) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля. Индекс = " + index);
        }

        if (index >= resultSize) {
            throw new IndexOutOfBoundsException("Указанный индекс больше результирующего размера списка." +
                    " Индекс = " + index + ", результирующий размер списка = " + resultSize);
        }
    }

    private void checkListEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        ListItem<E> item = head;
        StringBuilder stringBuilder = new StringBuilder("[");

        while (item.getNext() != null) {
            stringBuilder.append(item.getData())
                    .append(", ");

            item = item.getNext();
        }

        stringBuilder.append(item.getData())
                .append("]");

        return stringBuilder.toString();
    }
}
