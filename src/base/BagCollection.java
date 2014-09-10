package base;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Nick on 9/7/2014.
 */
public class BagCollection<T> implements AccessOps1<T>, AccessOps2<BagCollection<T>> {
    private static final int DEFAULT_SIZE = 10;
    private int size, itemCount = 0;
    private Object[] basis;

    public BagCollection() {
        this(DEFAULT_SIZE);
    }

    public BagCollection(int InitialSize) {
        if (InitialSize >= 0) {
            this.size = InitialSize;
            basis = new Object[this.size];
        } else {
            throw new IllegalArgumentException("Invalid capacity was specified: " + InitialSize);
        }

        System.out.println(">>> Bag set with initial capacity of " + this.size);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.itemCount;
    }

    public int getCapacity() {
        return this.size;
    }

    @Override
    public void add(T element) {
        if (itemCount == basis.length) {
            expand();
        }
        itemCount++;
        basis[itemCount - 1] = element;

        System.out.println("Added new element '" + element.toString() + "'");
    }

    @Override
    // Can only remove first occurrence
    public void remove(T element) {
        int result = contains(element);

        if (result >= 0) {
            removeInternal(result);
        }
    }

    private void removeInternal(int index) {
        if (index < itemCount) {
            System.arraycopy(basis, index + 1, basis, index, (itemCount - index) - 1);
            basis[itemCount - 1] = null;
            itemCount -= 1;
            System.out.println("Removed item at index " + index);
            System.out.println(this.toString());
        } else {
            System.out.println("Nothing removed");
        }
    }

    @Override
    public void removeRandom() {
        Random gen = new Random();
        int selection = gen.nextInt(size - 1);
        System.out.println("Randomly selected item at position " + selection + " ('"
                + basis[selection].toString() + "') for removal");
        remove((T) basis[selection]);
    }

    @Override
    // Only locates first occurrence of 'element'
    public int contains(T element) {
        for (int index = 0; index < itemCount; index++) {
            if (basis[index].equals(element)) {
                System.out.println("Found element '" + element.toString() + "' at index " + index);
                markItem(index);
                return index;
            }
        }
        System.out.println("The element '" + element.toString() + "' is not contained in this collection");
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder collect = new StringBuilder();
        collect.append("{");
        for (int index = 0; index < itemCount; index++) {
            collect.append(basis[index].toString());
            if (index < itemCount - 1) {
                collect.append(",");
            }
        }
        collect.append("}");
        return collect.toString();
    }

    @Override
    public void addAll(BagCollection<T> srcCollection, BagCollection<T> destCollection) {

    }

    @Override
    public BagCollection<T> union(BagCollection<T> col1, BagCollection<T> col2) {
        return null;
    }

    @Override
    public Boolean equals(BagCollection<T> col1, BagCollection<T> col2) {
        return null;
    }

    private void expand() {
        size += size >> 1;
        basis = Arrays.copyOf(basis, size);

        System.out.println("> Capacity expanded to " + size);
    }

    private void markItem(int basisIndex) {
        String toString = this.toString();
        StringBuilder collect = new StringBuilder();
        collect.append(toString);
        collect.append("\n");
        int appendCount = toString.indexOf(",");
        int commaPos = appendCount;
        collect.append(" ");
        for (int markCount = 0; markCount < basisIndex; markCount++) {
            for (int n = 0; n < appendCount; n++) {
                collect.append(" ");
            }
            appendCount = toString.indexOf(",", commaPos + 1) - commaPos;
            commaPos = toString.indexOf(",", commaPos + 1);
        }
        collect.append("^");
        System.out.println(collect.toString());
    }
}
