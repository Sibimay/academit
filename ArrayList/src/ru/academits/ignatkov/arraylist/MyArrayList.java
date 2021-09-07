package ru.academits.ignatkov.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше нуля");
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась!");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список изменился!");
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <E1> E1[] toArray(E1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (E1[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int objectIndex = indexOf(o);

        if (objectIndex == -1) {
            return false;
        }

        remove(objectIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index, size + collection.size());
        ensureCapacity(size + collection.size());

        System.arraycopy(elements, index, elements, index + collection.size(), size - index);

        int i = index;

        for (E e : collection) {
            elements[i] = e;
            i++;
        }

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int currentSize = size;

        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                remove(i);
                i--;
            }
        }

        return currentSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int currentSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                continue;
            }

            remove(i);
            i--;
            modCount++;
        }

        return currentSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size + 1);

        if (size >= elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);
        E deletedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        modCount++;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);

            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize() {
        if (size != elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    private void checkIndex(int index, int maxIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля. Индекс = " + index);
        }

        if (index >= maxIndex) {
            throw new IndexOutOfBoundsException("Указанный индекс больше размера списка." +
                    " Индекс = " + index + ", максимальный индекс = " + maxIndex);
        }
    }

    public void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length == 0 ? 1 : elements.length * 2);
    }
}
