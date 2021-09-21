package ru.academits.ignatkov.list;

class ListItem<E> {
    private E data;
    private ListItem<E> next;

    ListItem(E data) {
        this.data = data;
    }

    ListItem(E data, ListItem<E> next) {
        this.data = data;
        this.next = next;
    }

    E getData() {
        return data;
    }

    void setData(E data) {
        this.data = data;
    }

    ListItem<E> getNext() {
        return next;
    }

    void setNext(ListItem<E> next) {
        this.next = next;
    }
}
