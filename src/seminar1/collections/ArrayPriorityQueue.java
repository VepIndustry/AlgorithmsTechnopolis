package seminar1.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private Object[] elementData;
    private Comparator comparator;
    private int end = 0;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<Key> comparator) {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    private ArrayPriorityQueue(ArrayPriorityQueue queue) {
        this.elementData = queue.elementData;
        this.comparator = queue.comparator;
        this.end = queue.end;
    }


    @Override
    public void add(Key key) {
        elementData[end] = key;
        end++;
        if (end == elementData.length) grow();
        siftUp();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Key peek() {
        return (Key)elementData[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Key extractMin() {
        Key result = (Key)elementData[0];
        end--;
        elementData[0] = elementData[end];

        if (size() < elementData.length / 4) shrink();

        siftDown();
        return result;
    }

    @Override
    public boolean isEmpty() {
        return end == 0;
    }

    @Override
    public int size() {
        return end;
    }

    @SuppressWarnings("unchecked")
    private void siftUp() {
        //Корень всего - самый маленький элемент
        //поднимаем элемент в самом конце массива
        int element = end - 1;
        int parent = (element + 1) / 2 - 1;
        while(true) {
            if (element != 0 && greater(parent, element)) {
                Key buf = (Key)elementData[element];
                elementData[element] = elementData[parent];
                elementData[parent] = buf;
                element = parent;
                parent = (element + 1) / 2 - 1;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDown() {
        //Опускаем вниз начиная в корня если он больше чем сыновья
        int leftChild = 1;
        int rightChild = 2;
        int smallestChild = 0;
        int parent = 0;
        while (true) {
            if (leftChild < end && greater(smallestChild, leftChild)) {
                smallestChild = leftChild;
            }

            if (rightChild < end && greater(smallestChild, rightChild)) {
                smallestChild = rightChild;
            }

            if (smallestChild == parent) {
                break;
            }

            Key buffer = (Key)elementData[smallestChild];
            elementData[smallestChild] = elementData[parent];
            elementData[parent] = buffer;
            parent = smallestChild;
            leftChild = (parent + 1) * 2 - 1;
            rightChild = leftChild + 1;
        }
    }

    private void grow() {
        changeCapacity(((int) (elementData.length * 1.5)));
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void shrink() {
        changeCapacity((elementData.length / 2));
    }

    @SuppressWarnings("unchecked")
    private boolean greater(int i, int j) {
        return comparator == null
                ? ((Key)elementData[i]).compareTo((Key)elementData[j]) > 0
                : comparator.compare((Key)elementData[i], (Key)elementData[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ArrayPriorityQueueIterator();
    }

    private class ArrayPriorityQueueIterator implements Iterator<Key> {

        @SuppressWarnings("unchecked")
        private ArrayPriorityQueue queue = new ArrayPriorityQueue<Key>(ArrayPriorityQueue.this);

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        @SuppressWarnings("unchecked")
        public Key next() {
            return (Key)queue.extractMin();
        }

    }
}
