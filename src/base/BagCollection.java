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
    }

    @Override
    public void add(T element) {
        if (itemCount == basis.length) {
            expand();
        }
        itemCount++;
        basis[itemCount - 1] = element;
    }

    @Override
    public void remove(T element) {
        for (int index = 0; index < itemCount; index++) {
            if (basis[index].equals(element)) {
                System.arraycopy(basis, index + 1, basis, index, itemCount - (index + 1));
                break;
            }
        }
    }

    @Override
    public void removeRandom() {
        Random gen = new Random();
        remove((T) basis[gen.nextInt(bagSize - 1)]);
    }

    @Override
    public boolean contains(T element) {
        for (int index = 0; index < itemCount; index++) {
            if (basis[index].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private void expand() {
        bagSize += bagSize >> 1;
        basis = Arrays.copyOf(basis, bagSize);
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
