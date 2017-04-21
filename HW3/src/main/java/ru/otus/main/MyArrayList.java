package ru.otus.main;

import java.util.*;


public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] elements = (T[]) new Object[DEFAULT_SIZE];

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
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(size + 1);
        elements[size++] = (T) o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        checkArrayRange(index);
        int capacity = size + c.size();
        ensureCapacity(capacity);
        System.arraycopy(elements, index, elements, index + c.size(), this.size - index);
        System.arraycopy(c.toArray(), 0, elements, index, c.size());
        size = capacity;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        checkArrayRange(index);
        return (T) elements[index];
    }

    @Override
    public Object set(int index, Object element) {
        checkArrayRange(index);
        T value = elements[index];
        elements[index] = (T) element;
        return value;
    }

    @Override
    public void add(int index, Object element) {
        checkArrayRange(index);
        int newCapacity = size + 1;
        ensureCapacity(newCapacity);
        System.arraycopy(this.elements, index, this.elements, index + 1, newCapacity - index - 1);
        this.elements[index] = (T) element;
        this.size++;
    }

    @Override
    public T remove(int index) {
        checkArrayRange(index);
        T value = elements[index];
        elements[index] = null;
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if(elements[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        for (int i = 0; i < size; ) {
            if (!c.contains(elements[i])) {
                remove(i);
            } else {
                i++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        for (int i = 0; i < size; ) {
            if (c.contains(elements[i])) {
                remove(i);
            } else {
                i++;
            }
        }
        return true;
    }


    @Override
    public boolean containsAll(Collection c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    private class MyIterator implements Iterator {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Object next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            Object[] elements = MyArrayList.this.elements;
            if (cursor >= elements.length)
                throw new ConcurrentModificationException();
            return elements[lastRet = cursor++];
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

    private class MyListIterator extends MyIterator implements ListIterator {
        MyListIterator(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public Object previous() {
            int i = cursor - 1;
            if (i < 0){
                throw new NoSuchElementException();
            }
            Object[] elements = MyArrayList.this.elements;
            if (i >= elements.length){
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return elements[lastRet = i];
        }

        public void set(Object e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Object e) {
            try {
                int i = cursor;
                MyArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public Object[] toArray(Object[] a) {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new MyListIterator(index);
    }


    private void checkArrayRange(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
    }

    private void ensureCapacity(int newSize) {
        if (elements.length <= newSize) {
            elements = Arrays.copyOf(elements, getNewCapacity());
        }
    }

    private int getNewCapacity() {
        return elements.length + DEFAULT_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        if (size != that.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, that.elements);

    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        return '{' + Arrays.toString(elements) + '}';
    }
}
