package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedDeque() {
        head = new Node<Item>(null);
        tail = new Node<Item>(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void pushFront(Item item) {
        Node<Item> node = new Node<Item>(item, head, head.next);
        head.next = node;
        node.next.prev = node;
        size++;
    }

    @Override
    public void pushBack(Item item) {
        Node<Item> node = new Node<Item>(item, tail.prev, tail);
        tail.prev = node;
        node.prev.next = node;

        size++;
    }

    @Override
    public Item popFront() {
        Node<Item> current = head.next;
        Node<Item> next = current.next;
        head.next = next;
        next.prev = head;

        size--;
        return current.item;
    }

    @Override
    public Item popBack() {
        Node<Item> current = tail.prev;
        Node<Item> prev = current.prev;

        prev.next = tail;
        tail.prev = prev;

        size--;
        return current.item;
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
            return node.next != tail;
        }

        @Override
        public Item next() {
            node = node.next;
            return node.item;
        }
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
