package base;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Nick on 9/7/2014.
 */
public class BagCollection<T> implements AccessOps1<T>, AccessOps2<BagCollection<T>> {
    private static final int DEFAULT_SIZE = 10;

    // Debugging console output adds unneeded complexity. Set to false otherwise.
    private static final boolean DEBUG = true;

    private int max, itemCount = 0;

    // Backing object
    private Object[] basis;

    public BagCollection() {
        this(DEFAULT_SIZE);
    }

    public BagCollection(int InitialSize) {
        // Only positive values are allowed (0 is ok)
        if (InitialSize >= 0) {
            this.max = InitialSize;
            this.basis = new Object[this.max];
        } else {
            throw new IllegalArgumentException("Invalid capacity was specified: " + InitialSize);
        }

        System.out.println(">>> Bag set with initial capacity of " + this.max);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        // In this case, size means how many items are contained in the collection
        return this.itemCount;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public void add(T element) {
        // Expand items == capacity
        if (this.itemCount == this.basis.length) {
            expand();
        }
        this.itemCount++;
        this.basis[this.itemCount - 1] = element;

        System.out.println("Added new element '" + element.toString() + "'");
    }

    // Extra: Retrieve item at index
    public T get(int index){
        if(index < this.itemCount) {
            return (T) this.basis[index];
        } else {
            throw new IndexOutOfBoundsException("The collection index specified is out of bounds.");
        }
    }

    @Override
    // Remove by comparison
    public T remove(T element) {
        int result = contains(element);

        if (result >= 0) {
            return removeInternal(result);
        } else {
            return null;
        }
    }

    // All remove actions are by index internally
    private T removeInternal(int index) {
        T output;

        if (index < this.itemCount) {
            output= (T) this.basis[index];

            // Copy from index + 1 to end - 1
            System.arraycopy(this.basis, index + 1, this.basis, index, (this.itemCount - index) - 1);

            // Then null the last item
            this.basis[this.itemCount - 1] = null;

            this.itemCount--;
            System.out.println("Removed item at index " + index);
            System.out.println(this.toString());

            return output;
        } else {
            throw new IndexOutOfBoundsException("The collection index specified is out of bounds.");
        }
    }

    @Override
    public T removeRandom() {
        Random gen = new Random();

        // Remove by item count since the collection doesn't shrink
        int selection = gen.nextInt(this.itemCount - 1);

        System.out.println("Randomly selected item at position " + selection + " ('"
                + this.basis[selection].toString() + "') for removal");
        return remove((T) this.basis[selection]);
    }

    @Override
    // Only locates first occurrence of 'element'
    public int contains(T element) {
        for (int index = 0; index < this.itemCount; index++) {
            if (this.basis[index].equals(element)) {
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
        if(DEBUG) {
            StringBuilder collect = new StringBuilder();
            collect.append("{");
            for (int index = 0; index < this.itemCount; index++) {
                collect.append(this.basis[index].toString());
                if (index < this.itemCount - 1) {
                    collect.append(",");
                }
            }
            collect.append("}");
            return collect.toString();
        } else {
            return "BagCollection<T> with " + this.itemCount + " elements.";
        }
    }

    @Override
    public void addAll(BagCollection<T> source) {
        for(int index = 0; index < source.size(); index++){
            this.add(source.get(index));
        }
    }

    @Override
    public BagCollection<T> union(BagCollection<T> source) {
        // Set output to exact size of union
        BagCollection<T> output = new BagCollection<T>(source.size() + this.itemCount);
        // Add'em up
        output.addAll(this);
        output.addAll(source);
        return output;
    }

    public Boolean equals(BagCollection<T> subject){
        // Check size first
        if(subject.size() == this.max){
            // Union to temp
            BagCollection<T> temp = this.union(subject);
            T item;

            for(int index = 0; index < temp.size(); index++){
                // Hold item
                item = subject.get(index);

                // Remove from temp
                if(temp.remove(item) == null) {
                    return false;
                } else {
                    // Temp should still contain a duplicate if equal
                    if (temp.contains(item) < 0) {
                        return false;
                    } else {
                        // Remove the item's pair
                        temp.remove(item);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void expand() {
        // Bit shift to expand gracefully
        this.max += this.max >> 1;
        this.basis = Arrays.copyOf(this.basis, this.max);

        System.out.println("> Capacity expanded to " + this.max);
    }

    // Adds nice console output for debugging.
    private void markItem(int basisIndex) {
        if(DEBUG) {
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
}
