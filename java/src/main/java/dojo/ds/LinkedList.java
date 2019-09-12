package dojo.ds;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    class Node<E> {
        E value;
        Node<E> next;
        public Node(E value) {
            this.value = value;
        }
    }
    class LinkedListIterator<E> implements Iterator<E> {

        LinkedList<E> list;
        Node<E> cursor;
        public LinkedListIterator(LinkedList<E> list) {
            this.list = list;
            cursor = (Node<E>) list.head;
        }
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E value = cursor.value;
            cursor = cursor.next;
            return value;
        }
    }
    Node<E> head;
    Node<E> tail;
    int size;

    public int size() {
        return size;
    }

    public void add(E value) {
        if (size == 0) {
            head = new Node<>(value);
            tail = head;
        } else {
            tail.next = new Node<>(value);
            tail = tail.next;
        }
        size += 1;
    }

    public Node<E> getNodeAt(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
        Node<E> cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor;
    }

    public E get(int index) {
        return getNodeAt(index).value;
    }

    public void remove(int index) {
        if (size == 0) return;
        Node<E> node;
        if (index == 0) {
            node = head;
            head = head.next;
        } else {
            Node<E> prev = getNodeAt(index-1);
            node = prev.next;
            prev.next = node.next;
            if (index == size -1) tail = prev;
        }
        node.next = null;
        size -= 1;
    }

    public void insert(int index, E value) {
        if (index >= size) throw new IndexOutOfBoundsException();
        Node<E> node = new Node<>(value);
        if (index == 0) {
            node.next = head.next;
            head = node;
        } else {
            Node<E> prev = getNodeAt(index-1);
            node.next = prev.next;
            prev.next = node;
        }
        size += 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(this);
    }
}
