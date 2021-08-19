package ru.academits.ignatkov.arraylist;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] objects;
    private int size;
    private int modifyCount;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше нуля");
        }

        objects = (T[]) new Object[capacity];
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
        for (T object : objects) {
            if (object.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objects, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Вместо массива передан null");
        }

        if (a.length < size) {
            return (T1[]) Arrays.copyOf(objects, size, a.getClass());
        }

        System.arraycopy(objects, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T element) {
        if (objects.length < size + 1) {
            increaseCapacity();
        }

        objects[size] = element;
        size++;
        modifyCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            int elementIndex = -1;

            for (int i = 0; i < objects.length; i++) {
                if (objects[i] == o) {
                    elementIndex = i;
                    break;
                }
            }

            if (objects.length - 1 - elementIndex >= 0) {
                System.arraycopy(objects, elementIndex + 1, objects, elementIndex, objects.length - 1 - elementIndex);
            }

            size--;
            modifyCount--;
            objects = Arrays.copyOf(objects, size);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> elements) {
        //todo add exception if elements null
        //todo add condition if elements size = 0

        if (objects.length < size + elements.size()) {
            increaseCapacity();
        }

        for (T element : elements) {
            objects[size] = element;
            size++;
            modifyCount++;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> elements) {
        //todo add exception if elements null
        //todo add condition if elements size = 0
        //todo exception if index <= 0
        //todo exception if index > objects.length

        int newListSize = size + elements.size();
        int oldListSize = size;

        while (objects.length < newListSize) {
            increaseCapacity();
        }

        objects = Arrays.copyOf(objects, newListSize);

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        //todo exeption
        return objects[index];
    }

    @Override
    public T set(int index, T element) {
        //todo exception
        objects[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        try {
            throw new ExecutionControl.NotImplementedException("Method is not implemented");
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        try {
            throw new ExecutionControl.NotImplementedException("Method is not implemented");
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        try {
            throw new ExecutionControl.NotImplementedException("Method is not implemented");
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }

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
        if (capacity == objects.length) {
            return;
        }

        objects = Arrays.copyOf(objects, capacity);
    }

    private void increaseCapacity() {
        if (objects.length == 0) {
            objects = Arrays.copyOf(objects, 10);
            return;
        }

        objects = Arrays.copyOf(objects, objects.length * 2);
    }
}
