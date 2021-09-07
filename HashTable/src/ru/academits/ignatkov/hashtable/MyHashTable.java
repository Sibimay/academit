package ru.academits.ignatkov.hashtable;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private int size;
    private final ArrayList<E>[] lists;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        lists = (ArrayList<E>[]) new ArrayList[10];
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше или равна нулю. Вместимость списка " + capacity);
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
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
        int index = getElementIndex(o);

        if (lists[index] != null) {
            return lists[index].contains(o);
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = 0;
        private final int myModCount = modCount;
        private int currentListIndex = -1;
        private int countElement = 0;

        @Override
        public boolean hasNext() {
            return countElement != size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась!");
            }

            if (myModCount != modCount) {
                throw new ConcurrentModificationException("Список изменился!");
            }

            if (lists[currentIndex] == null || currentListIndex == lists[currentIndex].size() - 1) {
                currentListIndex = -1;
                currentIndex++;
            }

            while (lists[currentIndex] == null || lists[currentIndex].size() - 1 == currentListIndex) {
                currentIndex++;
            }

            countElement++;
            currentListIndex++;

            return lists[currentIndex].get(currentListIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] items = new Object[size];
        int index = 0;

        for (E element : this) {
            items[index] = element;
            index++;
        }

        return items;
    }

    @Override
    public <E1> E1[] toArray(E1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив пустой!");
        }

        Object[] items = toArray();

        if (a.length < size) {
            //noinspection unchecked
            return (E1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getElementIndex(e);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(e);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean isDeleted = false;
        int index = getElementIndex(o);

        if (lists[index] != null) {
            isDeleted = lists[index].remove(o);
        }

        if (lists[index].size() == 0) {
            lists[index] = null;
        }

        if (isDeleted) {
            size--;
            modCount++;
        }

        return isDeleted;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция!");
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (size == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
//    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция!");
        }

        if (c.size() == 0) {
            return false;
        }

        int tmp = modCount;

        for (Object e : c) {
            int index = getElementIndex(e);

            //noinspection SuspiciousMethodCalls
            while (lists[index] != null && lists[index].remove(c)) {
                size--;
                modCount++;
            }
        }

        return modCount != tmp;
    }

    private int getElementIndex(Object element) {
        if (element == null) {
            return 0;
        }
        return Math.abs(element.hashCode() % lists.length);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция!");
        }

        int tmp = modCount;

        for (Collection<E> p : lists) {
            if (p != null) {
                int currentSize = p.size();

                if (p.retainAll(c)) {
                    size -= currentSize - p.size();
                    modCount++;
                }
            }
        }

        return modCount != tmp;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                str.append("Ключ ")
                        .append(i).append(": ")
                        .append(lists[i].toString())
                        .append(System.lineSeparator());
            }
        }

        return str.toString();
    }
}
