package ru.academits.ignatkov.list;

public class ListItem<E> {
    private E data;
    private ListItem<E> next;

    public ListItem() {

    }

    public ListItem(E data) {
        this.data = data;
    }

    public ListItem(E data, ListItem<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public ListItem<E> getNext() {
        return next;
    }

    public void setNext(ListItem<E> next) {
        this.next = next;
    }
}
