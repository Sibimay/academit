package ru.academits.ignatkov.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] objects;
    private int size;
    private int modifyCount;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше нуля");
        }

        objects = (E[]) new Object[capacity];
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
        if (o != null) {
            for (E object : objects) {
                if (object.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int myModifyCount = modifyCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась!");
            }
            if (myModifyCount != modifyCount) {
                throw new ConcurrentModificationException("Список изменился!");
            }
            currentIndex++;
            return objects[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objects, size);
    }

    @Override
    public <E1> E1[] toArray(E1[] a) {
        if (a.length < size) {
            return (E1[]) Arrays.copyOf(objects, size, a.getClass());
        }

        System.arraycopy(objects, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size + 1);

        objects[size] = element;
        size++;
        modifyCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null && contains(o)) {
            int elementIndex = -1;

            for (int i = 0; i < objects.length; i++) {
                if (objects[i].equals(o)) {
                    elementIndex = i;
                    break;
                }
            }

            System.arraycopy(objects, elementIndex + 1, objects, elementIndex, size - elementIndex - 1);

            size--;
            modifyCount++;
            objects = Arrays.copyOf(objects, size);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        long occurrencesCount = c.stream()
                .filter(this::contains)
                .count();

        return occurrencesCount == (long) c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        ensureCapacity(size + elements.size());

        elements.forEach(this::add);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        checkIndex(index);
        ensureCapacity(size + elements.size());

        System.arraycopy(objects, index, objects, index + elements.size(), size - index);

        int i = index;

        for (E e : elements) {
            objects[i] = e;
            i++;
        }

        size += elements.size();
        modifyCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.stream()
                .filter(this::contains)
                .forEach(this::remove);

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        MyArrayList<Integer> indexes = new MyArrayList<>(c.size());

        c.forEach(x -> {
            int index = indexOf(x);

            if (index != -1) {
                indexes.add(indexOf(x));
            }
        });

        indexes.trimToSize();

        MyArrayList<E> newArrayList = new MyArrayList<>(indexes.size());

        for (int i = 0; i < objects.length; i++) {
            if (indexes.contains(i)) {
                newArrayList.add(objects[i]);
            }
        }

        objects = newArrayList.objects;
        size = newArrayList.size;
        modifyCount++;

        return true;
    }

    @Override
    public void clear() {
        size = 0;
        trimToSize();

        modifyCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return objects[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = objects[index];
        objects[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        ensureCapacity(size + 1);

        System.arraycopy(objects, index, objects, index + 1, size - index);

        set(index, element);
        size++;
        modifyCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E object = objects[index];
        remove(object);

        return object;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;

        for (int i = objects.length - 1; i >= 0; i--) {
            if (objects[i].equals(o)) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        System.out.println("Method is not implemented");

        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        System.out.println("Method is not implemented");

        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        System.out.println("Method is not implemented");

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < objects.length; i++) {
            stringBuilder.append(objects[i]);

            if (i != objects.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= objects.length) {
            return;
        }

        objects = Arrays.copyOf(objects, capacity);
    }

    public void trimToSize() {
        if (size == objects.length) {
            return;
        }

        objects = Arrays.copyOf(objects, size);
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Указанный индекс меньше нуля");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Указанный индекс больше размера списка");
        }
    }
}
