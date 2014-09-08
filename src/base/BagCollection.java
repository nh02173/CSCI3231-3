package base;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Nick on 9/7/2014.
 */
public class BagCollection<T> implements AccessOps1<T> {
    private static final int DEFAULT_SIZE = 10;

    private int bagSize, itemCount = 0;
    private Object[] basis;

    public BagCollection() {
        this(DEFAULT_SIZE);
    }

    public BagCollection(int InitialSize) {
        if (InitialSize >= 0) {
            this.bagSize = InitialSize;
            basis = new Object[this.bagSize];
        } else {
            throw new IllegalArgumentException("Invalid capacity was specified: " + InitialSize);
        }

        System.out.println("Bag set with initial size of " + this.bagSize);
    }

    @Override
    public void add(T element) {
        if (itemCount == basis.length) {
            expand();
        }
        itemCount++;
        basis[itemCount - 1] = element;

        System.out.println("Added new element " + element.toString());
    }

    @Override
    public void remove(T element) {
        int result = contains(element);

        if(result > 0){
            System.arraycopy(basis, result + 1, basis, result, itemCount - (result + 1));
            System.out.println("Removed element " + element.toString() + " at position " + result);
        } else {
            System.out.println("Noting removed");
        }
    }

    @Override
    public void removeRandom() {
        Random gen = new Random();
        remove((T) basis[gen.nextInt(bagSize - 1)]);
    }

    @Override
    public int contains(T element) {
        for (int index = 0; index < itemCount; index++) {
            if (basis[index].equals(element)) {
                System.out.println("Found element '" + element.toString() + "' at index " + index);
                return index;
            }
        }
        System.out.println("Element '" + element.toString() + "' is not contained in this collection");

        return 0;
    }

    private void expand() {
        bagSize += bagSize >> 1;
        basis = Arrays.copyOf(basis, bagSize);

        System.out.println("Capacity expanded to " + bagSize);
    }

    @Override
    public boolean isEmpty() {
        return bagSize == 0;
    }

    @Override
    public int size() {
        return this.itemCount;
    }

    public int getCapacity() {
        return this.bagSize;
    }
}
