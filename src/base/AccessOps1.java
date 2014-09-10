package base;

/**
 * Created by Nick on 9/7/2014.
 */
public interface AccessOps1<T> {
    void add(T element);
    T remove(T element);
    T removeRandom();
    boolean isEmpty();
    int contains(T element);
    int size();
}
