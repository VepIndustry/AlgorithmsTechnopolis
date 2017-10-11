package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 * <p>
 * Time = O(k),
 * k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private Integer firstNumber, secondNumber;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
//        if (first.hasNext()) {
//            firstNumber = first.next();
//        }
//        if (second.hasNext()) {
//            secondNumber = second.next();
//        }
    }

    @Override
    public boolean hasNext() {
        return first.hasNext() || second.hasNext();
    }

    @Override
    public Integer next() {
        if (firstNumber == null && first.hasNext()) firstNumber = first.next();
        if (secondNumber == null && second.hasNext()) secondNumber = second.next();

        if (firstNumber == null) return second.next();
        if (secondNumber == null) return first.next();

        int result;

        if (firstNumber < secondNumber) {
            result = firstNumber;
            firstNumber = null;
        } else {
            result = secondNumber;
            secondNumber = null;
        }
        return result;
    }
}
