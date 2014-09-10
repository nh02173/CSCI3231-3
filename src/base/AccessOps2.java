package base;

/**
 * Created by Nick on 9/7/2014.
 * Only valid for the special case custom collection type BagCollection.
 */
public interface AccessOps2<T> {
    void addAll(T srcCollection, T destCollection);
    T union(T col1, T col2);
    Boolean equals(T col1, T col2);
}
