package ru.academits.ignatkov.list;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int getSize() {
        return count;
    }

    public E getFirstElement() {
        checkListEmpty();

        return head.getData();
    }

    public E getDataByIndex(int index) {
        return getElementByIndex(index).getData();
    }

    public E setElementDataByIndex(int index, E data) {
        checkIndex(index);

        ListItem<E> element = getElementByIndex(index);
        E oldData = element.getData();

        element.setData(data);

        return oldData;
    }

    public E deleteElementByIndex(int index) {
        checkIndex(index);

        ListItem<E> element = getElementByIndex(index);
        ListItem<E> previousElement = getElementByIndex(index - 1);
        ListItem<E> nextElement = element.getNext();

        if (nextElement != null) {
            previousElement.setNext(getElementByIndex(index + 1));
        } else {
            previousElement.setNext(null);
        }

        count--;

        return element.getData();
    }

    private ListItem<E> getElementByIndex(int index) {
        checkIndex(index);

        ListItem<E> element = head;
        int i = 0;

        while (i != index) {
            element = element.getNext();
            i++;
        }

        return element;
    }

    public void addFirstElement(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addElementByIndex(int index, E data) {
        checkIndex(index);

        if (index == 0) {
            addFirstElement(data);
        } else {
            ListItem<E> previousElement = getElementByIndex(index - 1);
            previousElement.setNext(new ListItem<>(data, previousElement.getNext()));
            count++;
        }
    }

    public boolean deleteItemByData(E data) {
        ListItem<E> element = head;
        int index = 0;

        while (element.getData() != data) {
            element = element.getNext();

            if (element == null) {
                return false;
            }

            index++;
        }

        deleteElementByIndex(index);

        return true;
    }

    public E deleteFirstElement() {
        checkListEmpty();

        E oldData = head.getData();

        head = head.getNext();
        count--;

        return oldData;
    }

    public void reverse() {
        checkListEmpty();


    }


    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля");
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Указанный индекс больше размера списка");
        }
    }

    private void checkListEmpty() {
        if (count == 0) {
            throw new NullPointerException("Список пуст");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<E> element = head;

        for (int i = 0; i < count; i++) {

            stringBuilder.append(element.getData());

            if (i != count - 1) {
                stringBuilder.append(", ");
            }

            element = element.getNext();
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
