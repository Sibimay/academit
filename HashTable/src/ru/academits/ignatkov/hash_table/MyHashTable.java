package ru.academits.ignatkov.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        lists = (ArrayList<E>[]) new ArrayList[10];
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
        private int listIndex = -1;
        private int tableIndex = -1;

        public boolean hasNext() {
            return listIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of the table");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("HashTable was changed during iteration");
            }

            while (hasNext()) {
                if (lists[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    tableIndex++;

                    if (tableIndex == lists[arrayIndex].size()) {
                        arrayIndex++;
                        tableIndex = -1;
                    } else {
                        listIndex++;
                        return lists[arrayIndex].get(tableIndex);
                    }
                }
            }

            return null;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] items = new Object[size];
        int i = 0;

        for (E element : this) {
            items[i] = element;
            i++;
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
        boolean isDeleted = false;
        int index = getIndex(o);

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
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция!");
        }

        if (c.size() == 0) {
            return false;
        }

        int initialModCount = modCount;

        for (Collection<E> list : lists) {
            if (list != null) {
                int initialSize = list.size();

                if (list.removeAll(c)) {
                    size -= initialSize - list.size();
                    modCount++;
                }
            }
        }

        return modCount != initialModCount;
    }

    private int getIndex(Object element) {
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

        int initialModCount = modCount;

        for (Collection<E> list : lists) {
            if (list != null) {
                int initialSize = list.size();

                if (list.retainAll(c)) {
                    size -= initialSize - list.size();
                    modCount++;
                }
            }
        }

        return modCount != initialModCount;
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