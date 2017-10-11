package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(Item item) {
        elementData[size]=item;
        size++;
        if (size==elementData.length) grow();
    }

    @Override
    public Item pop() {
        if (isEmpty()) throw new EmptyStackException();
        size--;
        if (size<elementData.length/4) shrink();
        return elementData[size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        changeCapacity(((int) (elementData.length * 1.5)));
    }

    private void shrink() {
        changeCapacity(elementData.length/2);
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }
}