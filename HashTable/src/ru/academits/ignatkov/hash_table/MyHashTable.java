package ru.academits.ignatkov.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public MyHashTable() {
        this(DEFAULT_ARRAY_SIZE);
    }

    public MyHashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна нулю. Длина массива " + arrayLength);
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[arrayLength];
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private final int initialModCount = modCount;
        private int arrayIndex = 0;
        private int tableIndex = -1;
        private int listIndex = -1;

        public boolean hasNext() {
            return tableIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of the table");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("HashTable was changed during iteration");
            }

            while (true) {
                if (lists[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex == lists[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        tableIndex++;
                        return lists[arrayIndex].get(listIndex);
                    }
                }
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] items = new Object[size];
        int i = 0;

        for (E item : this) {
            items[i] = item;
            i++;
        }

        return items;
    }

    @Override
    public <E1> E1[] toArray(E1[] a) {
        if (a == null) {
            throw new NullPointerException("Вместо коллекции передан null");
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
        int index = getIndex(e);

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
        int index = getIndex(o);

        if (lists[index].size() == 0) {
            lists[index] = null;
        }

        int currentSize = size;

        if (lists[index] != null) {
            if (lists[index].remove(o)) {
                size--;
                modCount++;
            }
        }

        return currentSize != size;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Вместо коллекции передан null");
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
        if (c == null) {
            throw new NullPointerException("Вместо коллекции передан null");
        }

        if (c.size() == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Вместо коллекции передан null");
        }

        if (c.size() == 0) {
            return false;
        }

        int currentSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                size -= list.size();
                list.removeAll(c);
                size += list.size();
            }
        }

        if (currentSize != size) {
            modCount++;
        }

        return currentSize != size;
    }

    private int getIndex(Object item) {
        if (item == null) {
            return 0;
        }

        return Math.abs(item.hashCode() % lists.length);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Вместо коллекции передан null");
        }

        int currentSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                size -= list.size();
                list.retainAll(c);
                size += list.size();
            }
        }

        if (currentSize != size) {
            modCount++;
        }

        return currentSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                stringBuilder.append("Ключ ")
                        .append(i)
                        .append(": ")
                        .append(lists[i])
                        .append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }
}