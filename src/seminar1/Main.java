package seminar1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.ArrayStack;
import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.CyclicArrayQueue;
import seminar1.collections.IDeque;
import seminar1.collections.IQueue;
import seminar1.collections.IStack;
import seminar1.collections.LinkedDeque;
import seminar1.collections.LinkedQueue;
import seminar1.collections.LinkedStack;
import seminar1.collections.TwoStackQueue;
import seminar1.iterators.IncreasingIterator;
import seminar1.iterators.MergingIncreasingIterator;
import seminar1.iterators.MergingPeekingIncreasingIterator;
import seminar1.iterators.PeekingIncreasingIterator;

public class Main {

    public static void main(String[] args) {
//        System.out.println("LinkedStack");
//        IStack<Integer> stack = new LinkedStack<>();
//        for (int i = 0; i < 20; i++) {
//            stack.push(i);
//        }
//        for (int i : stack) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.print(stack.pop() + " ");
//        }
//        System.out.println();
//
//        System.out.println();
//        System.out.println("LinkedQueue");
        ArrayPriorityQueue<Integer> queue;
//        for (int i = 0; i < 3; i++) {
//            queue.enqueue(i);
//        }
//        for (int i : queue) {
//            System.out.print(i + " ");
//        }
//        System.out.println("\n___");
//        for (int i = 0; i < 20; i++) {
//            System.out.print(queue.dequeue() + " ");
//        }
//        System.out.println();
//

//             System.out.println();
//        System.out.println("TwoStackQueue");
//        queue = new CyclicArrayDeque<>();
//        for (int i = 0; i < 15; i++) {
//            queue.pushBack(i);
//        }
//        for (int i = 0; i < 15; i++) {
//            queue.pushFront(i - 15);
//        }
//        for (int i : queue) {
//            System.out.print(i + " ");
//        }
//        System.out.println("\n___");
//        for (int i = 0; i < 30; i++) {
//            System.out.print(queue.popFront() + " ");
//        }

//        Random random = new Random();
//        queue = new ArrayPriorityQueue<Integer>();
//        for (int i = 0; i < 15; i++) {
//            queue.add(random.nextInt(10));
//        }
//
//        for (int i : queue) {
//            System.out.print(i + " ");
//        }

        MergingPeekingIncreasingIterator iterator =
                new MergingPeekingIncreasingIterator(
                        new PeekingIncreasingIterator(0, 4, 8),
                        new PeekingIncreasingIterator(0, 4, 8),
                        new PeekingIncreasingIterator(0, 4, 8));

        int i = 1;
        while(iterator.hasNext()) {
            System.out.println(i++ + " " + iterator.next());
        }
    }
}
