package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comparator = c;
    }

    private T getMax(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T m = this.get(0);
        for (int i = 0; i < size(); i++) {
            T temp = this.get(i);
            if(c.compare(m, temp) < 0){
                m = temp;
            }
        }
        return m;
    }

    public T max(){
        return getMax(comparator);
    }

    public T max(Comparator<T> c){
        return getMax(c);

    }
}
