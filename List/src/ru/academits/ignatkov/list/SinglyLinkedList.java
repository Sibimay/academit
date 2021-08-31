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
        checkIndex(index, count);

        ListItem<E> element = getElementByIndex(index);
        E oldData = element.getData();

        element.setData(data);

        return oldData;
    }

    public E deleteElementByIndex(int index) {
        checkIndex(index, count);

        ListItem<E> element = getElementByIndex(index);
        E elementData = element.getData();

        if (index == 0) {
            deleteFirstElement();
        } else {
            ListItem<E> previous = getElementByIndex(index - 1);
            previous.setNext(element.getNext());
        }

        count--;

        return elementData;
    }

    private ListItem<E> getElementByIndex(int index) {
        checkIndex(index, count);

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
        checkIndex(index, count + 1);

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
        if (count == 0) {
            return;
        }

        for (ListItem<E> element = head.getNext(), previous = head, beforePrevious = null;
             element != null; previous = element, element = element.getNext()) {
            previous.setNext(beforePrevious);
            beforePrevious = previous;

            if (element.getNext() == null) {
                head = element;
                head.setNext(previous);
                return;
            }
        }
    }

    public SinglyLinkedList<E> cloneList() {
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        if (head == null) {
            return copiedList;
        }

        ListItem<E> copiedListElement = new ListItem<>(head.getData(), null);

        copiedList.head = copiedListElement;
        ListItem<E> element = head.getNext();

        while (element != null) {
            ListItem<E> nextCopiedListElement = new ListItem<>(element.getData(), null);
            copiedListElement.setNext(nextCopiedListElement);
            copiedListElement = nextCopiedListElement;

            element = element.getNext();
        }

        copiedList.count = count;

        return copiedList;
    }

    private void checkIndex(int index, int newCount) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля. Индекс = " + index);
        }

        if (index > newCount) {
            throw new IndexOutOfBoundsException("Указанный индекс больше или равен размеру списка." +
                    " Индекс = " + index + ", размер списка = " + newCount);
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
