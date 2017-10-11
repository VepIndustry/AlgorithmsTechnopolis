package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private Item[] elementData;
    private int start = 0, end = 0, size = 0;


    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public CyclicArrayQueue() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(Item item) {
        elementData[end] = item;
        size++;
        end++;
        if (size == elementData.length) {
            grow();
        } else {
            end = end == elementData.length ? 0 : end;
        }
    }

    @Override
    public Item dequeue() {
        Item result = elementData[start];
        size--;
        start = start == elementData.length - 1 ? 0 : start + 1;
        if (size < elementData.length / 4) {
            shrink();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private Item[] fill(Item[] newArray) {
        if (start <= end) {
            return Arrays.copyOf(Arrays.copyOfRange(elementData, start, end), newArray.length);
        } else {
            //Создаём массив нужных нам размеров и переносим всё туда, стоит учитывать что перенос будет со старыми значениями
            //start и end
            int j = 0;
            for (int i = start; i < elementData.length; i++, j++) {
                newArray[j] = elementData[i];
            }

            for (int i = 0; i < end; i++, j++) {
                newArray[j] = elementData[i];
            }
            return newArray;
        }
    }


    private void grow() {
        changeSize((int) (elementData.length * 1.5));
    }

    private void shrink() {
        changeSize(elementData.length / 2);
    }

    @SuppressWarnings("unchecked")
    private void changeSize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        elementData = fill(newArray);
        start = 0;
        end = size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayQueueIterator();
    }

    private class CyclicArrayQueueIterator implements Iterator<Item> {

        private int currentPosition = start;

        @Override
        public boolean hasNext() {
            return currentPosition != end;
        }

        @Override
        public Item next() {
            Item result = elementData[currentPosition];
            currentPosition = currentPosition == elementData.length - 1 ? 0 : currentPosition + 1;
            return result;
        }

    }
}
