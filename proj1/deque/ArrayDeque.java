package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private Object[] items;
    private int size;
    private int head;  // 指向第一个元素的索引
    private int tail;  // 指向下一个可插入位置的索引

    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque(){
        items = new Object[INITIAL_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    private int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int  index){
        return (index + 1) % items.length;
    }

    // @Override
    // public boolean isEmpty(){
    //     return size == 0;
    // }
    
    @Override
    public int size(){
        return size;
    }


    private void resize(int capacity){
        Object[] newItems = new Object[capacity];
        for(int i = 0; i < size ; i++){
            newItems[i] = items[(head + i) % items.length];
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    @Override
    public void addFirst(T elem){
        if(elem == null){
            throw new NullPointerException("Cannot add null item");
        }
        if(size == items.length){
            resize(items.length * 2);
        }
        head = minusOne(head);
        items[head] = elem;
        size++;
    }

    @Override
    public void addLast(T elem){
        if(elem == null){
            throw new NullPointerException("Cannot add null item");
        }
        if(size == items.length){
            resize(items.length * 2);
        }
        items[tail] = elem;
        tail = plusOne(tail);
        size++;
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T elem = (T)items[head];
        items[head] = null;
        head = plusOne(head);
        size--;

        if(items.length >= 16 && size < items.length / 4){
            resize(items.length / 2);
        }

        return elem;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }

        tail = minusOne(tail);
        T elem = (T)items[tail];
        items[tail] = null;   
        size--;

        if(items.length >= 16 && size < items.length / 4){
            resize(items.length / 2);
        }

        return elem;        
    }

    @Override
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.println(items[(head + i) % items.length] + " ");
        }
        System.out.println();
    }
    
    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return (T)items[(head + index) % items.length];
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Deque) || ((Deque<?>) o).size() != size()){
            return false;
        }
        if(o == this){
            return true;
        }
        for (int i = 0; i < size; i++) {
            Object item = ((Deque<?>)o).get(i);
            if(! (this.get(i).equals(item)) ){
                return false;
            }
            
        }
        return true;
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int pos = 0;

        @Override
        public boolean hasNext(){
            return this.pos < size; // 修改：只遍历有效元素
        }
        
        @Override
        public T next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            T ret = (T) items[(head + this.pos) % items.length];
            this.pos++;
            return ret;
        }
    }

}
