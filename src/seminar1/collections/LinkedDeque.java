package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void pushFront(Item item) {
        head = new Node<Item>(item, null, head);
        head.next.prev = head;
        size++;
    }

    @Override
    public void pushBack(Item item) {
        tail = new Node<Item>(item, tail, null);
        tail.prev.next = tail;
        size++;
    }

    @Override
    public Item popFront() {
        head = head.next;
        size--;
        return head.prev.item;
    }

    @Override
    public Item popBack() {
        tail = tail.prev;
        size--;
        return tail.next.item;
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
        private Node<Item> node = new Node<Item>(null, null, head);

        @Override
        public boolean hasNext() {
            return node != tail;
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
