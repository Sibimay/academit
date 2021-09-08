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
        checkIndex(index, size);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public E delete(int index) {
        checkIndex(index, size);

        ListItem<E> item = getItem(index);
        E deletedData = item.getData();

        if (index == 0) {
            deleteFirst();
        } else {
            ListItem<E> previous = getItem(index - 1);
            previous.setNext(item.getNext());
            size--;
        }


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
                    current = previous;
                    current.setNext(current.getNext().getNext());
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
        if (size == 0) {
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

        ListItem<E> copiedListItem = new ListItem<>(head.getData(), null);

        copiedList.head = copiedListItem;
        ListItem<E> item = head.getNext();

        while (item != null) {
            ListItem<E> nextCopiedListItem = new ListItem<>(item.getData(), null);
            copiedListItem.setNext(nextCopiedListItem);
            copiedListItem = nextCopiedListItem;

            item = item.getNext();
        }

        copiedList.size = size;

        return copiedList;
    }

    private void checkIndex(int index, int maxIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля. Индекс = " + index);
        }

        if (index >= maxIndex) {
            throw new IndexOutOfBoundsException("Указанный индекс больше или равен размеру списка." +
                    " Индекс = " + index + ", размер списка = " + maxIndex);
        }
    }

    private void checkListEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<E> item = head;

        if (size == 0) {
            return stringBuilder.append("]").toString();
        }

        for (int i = 0; i < size; i++) {
            stringBuilder.append(item.getData());

            if (i != size - 1) {
                stringBuilder.append(", ");
            }

            item = item.getNext();
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
