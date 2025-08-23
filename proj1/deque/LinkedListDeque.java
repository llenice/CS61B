package deque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{

    private class Node {
        private Node prev;
        private Node next;
        private T elem;
    }

    private int size;
    private final Node sentinel;

    public LinkedListDeque(){
        this.size = 0;
        this.sentinel = new Node();
        this.sentinel.elem = null;
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    @Override
    public void addFirst(T item){
        Node p = new Node();
        p.elem = item;
        p.next = sentinel.next;
        p.prev = sentinel;
        sentinel.next = p;
        p.next.prev = p;
        size++;
    }

    @Override
    public void addLast(T item){
        Node p = new Node();
        p.elem = item;
        p.next = sentinel;
        p.prev = sentinel.prev;
        sentinel.prev = p;
        p.prev.next = p;
        size++;
    }

    @Override
    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        Node p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size--;
        return p.elem;
    }

    @Override
    public T removeLast(){
        if(sentinel.prev == sentinel){
            return null;
        }
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size--;
        return p.elem;
    }

    @Override
    public int size(){
        return size;
    }

    public T get(int index){
        Node p = sentinel.next;
        while( index != 0 && p != sentinel){
            p = p.next;
            index--;
        }
        if(index == 0){
            return p.elem;
        }
        return null;
    }

    public T getRecursive(int index){
        Node p = sentinel.next;
        if(p == sentinel){
            return null;
        }
        if(index == 0){
            return p.elem;
        }
        LinkedListDeque<T> l = new LinkedListDeque<>();
        l.sentinel.next = p.next;
        return l.getRecursive(index - 1);
    }

    @Override
    public boolean isEmpty(){
        return sentinel.next == sentinel;
        // return size==0;
    }

    public void printDeque(){
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.elem + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Deque) || ((Deque<?>) o).size() != this.size()){
            return false;
        }
        if(o == this){
            return true;
        }
        for (int i = 0; i < this.size(); i++) {
            Object item = ((Deque<?>) o).get(i);
            if(!this.get(i).equals(item)){
                return false;
            }
        }
        return true;
    }


    private class LinkedListIterator implements Iterator<T> {
        private Node node;

        LinkedListIterator() {
            this.node = sentinel.next;
        }

        public boolean hasNext() {
            return this.node != sentinel;
        }

        public T next() {
            T ret = this.node.elem;
            this.node = this.node.next;
            return ret;
        }

    }
    
    // public static void main(String[] args) {
    //     LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
	// 	// should be empty

	// 	System.out.println(lld1.isEmpty());

	// 	lld1.addFirst(10);
	// 	// should not be empty
    //     lld1.printDeque();
	// 	System.out.println(lld1.isEmpty());

	// 	lld1.removeFirst();
	// 	lld1.printDeque();
	// 	System.out.println(lld1.isEmpty());
    // }
}
