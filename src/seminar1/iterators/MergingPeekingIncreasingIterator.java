package seminar1.iterators;

import java.util.Comparator;
import java.util.Iterator;

import seminar1.collections.ArrayPriorityQueue;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 * <p>
 * Time = O(n + k * log n),
 * n — количество итераторов
 * k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private Comparator<IPeekingIterator<Integer>> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    private ArrayPriorityQueue<Node> queue = new ArrayPriorityQueue<>();

    private class Node implements Comparable<Node>, IPeekingIterator<Integer> {
        @Override
        public Integer peek() {
            return iterator.peek();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        private IPeekingIterator<Integer> iterator;

        public Node(IPeekingIterator<Integer> iterator) {
            this.iterator = iterator;
        }

        @Override
        public int compareTo(Node o) {
            return comparator.compare(iterator, o.iterator);
        }
    }

    @SafeVarargs
    public MergingPeekingIncreasingIterator(IPeekingIterator<Integer>... peekingIterator) {
        for (IPeekingIterator<Integer> iterator : peekingIterator)
            if (iterator.hasNext())
                queue.add(new Node(iterator));
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Integer next() {
        Node min = queue.extractMin();
        int result = min.next();
        if (min.hasNext()) queue.add(min);
        return result;
    }
}
