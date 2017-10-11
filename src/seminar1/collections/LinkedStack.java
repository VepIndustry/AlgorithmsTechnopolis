package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException();
        head = new Node<>(item,head);
        size++;
    }

    @Override
    public Item pop() {
        if (isEmpty()) throw new RuntimeException();
        Item tmp = head.item;
        head=head.next;
        size--;
        return tmp;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        Node<Item> currentNode = new Node<>(null, head);

        @Override
        public boolean hasNext() {
            return currentNode.next!=(null);
        }

        @Override
        public Item next() {
            currentNode = currentNode.next;
            return currentNode.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}

class EmptyStackException extends RuntimeException {

}
