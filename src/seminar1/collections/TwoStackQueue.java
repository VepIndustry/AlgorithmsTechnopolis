package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        stack1 = new ArrayStack<Item>();
        stack2 = new ArrayStack<Item>();
    }

    @Override
    public void enqueue(Item item) {
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack1.size() == 0;
    }

    @Override
    public int size() {
        return stack1.size();
    }

    @Override
    public Iterator<Item> iterator() {
        return new TwoStackIterator();
    }

    private class TwoStackIterator implements Iterator<Item> {
        private IQueue<Item> queue = new LinkedQueue<>();

        public TwoStackIterator() {
            for (Item item : stack2) {
                queue.enqueue(item);
            }

            IStack<Item> buffer = new ArrayStack<>();
            for (Item item : stack1) {
                buffer.push(item);
            }
            for (Item item : buffer) {
                queue.enqueue(item);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Item next() {
            return queue.dequeue();
        }

    }

}
