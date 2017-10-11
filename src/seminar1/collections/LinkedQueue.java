package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        switch (size) {
        default:
            tail = new Node<Item>(item, tail);
            break;
        case 0:
            head = new Node<Item>(item, null);
            break;
        case 1:
            tail = new Node<Item>(item, head);
            break;
        }
        size++;
    }

    @Override
    public Item dequeue() {
        switch (size) {
        default:
            Node<Item> preHead = null, curNode = tail;

            while (preHead == null) {
                if (curNode.next == head) {
                    preHead = curNode;
                } else {
                    curNode = curNode.next;
                }
            }

            preHead.next = null;
            Item result = head.item;
            head = preHead;
            return result;
        case 1:
        case 2:
            size--;
            return head.item;
        case 0:
            throw new RuntimeException();
        }
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
        private Node<Item> node = tail;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            Item result = node.item;
            node = node.next;
            return result;
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
