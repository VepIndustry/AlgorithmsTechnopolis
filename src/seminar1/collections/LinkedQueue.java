package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head = new Node<Item>(null, null); // Указывает на элемент за областью данных
    private Node<Item> tail = new Node<Item>(null, head);
    private int size;

    @Override
    public void enqueue(Item item) {
        Node<Item> newNode = new Node<Item>(item);
        newNode.next = tail.next;
        tail.next = newNode;

        size++;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<Item> preHead = tail, node = tail;
        while(node.next != head) {
            preHead = node;
            node = node.next;
        }

        preHead.next = head;
        size--;
        return node.item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {
        private Node<Item> node = head;

        @Override
        public boolean hasNext() {
            return node != tail.next;
        }

        @Override
        public Item next() {
            if (node == tail) return null;
            Node<Item> curNode = tail;
            while(curNode.next != node) {
                curNode = curNode.next;
            }

            node = curNode;
            return node == tail ? null : node.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
