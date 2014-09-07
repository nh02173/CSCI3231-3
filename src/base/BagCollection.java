package base;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Nick on 9/7/2014.
 */
public class BagCollection<T> implements AccessOps1<T> {
    private static final int DEFAULT_SIZE = 10;

    private int bagSize, itemCount = 0, position = 0;
    private Object[] basis;

    public BagCollection(){
        this(DEFAULT_SIZE);
    }

    public BagCollection(int InitialSize){
        if(InitialSize >= 0) {
            this.bagSize = InitialSize;
            basis = new Object[this.bagSize];
        } else {
            throw new IllegalArgumentException("Invalid capacity was specified: " + InitialSize);
        }
    }

    @Override
    public void add(T element) {
        if((itemCount + 1) >= basis.length){
            expand();
        }
        itemCount++;
        basis[itemCount-1] = element;
    }

    @Override
    public void remove(T element) {
        for(int index = 0; index < itemCount; index++){
            if(basis[index] == element){
                System.arraycopy(basis,index+1,basis,index,itemCount-(index+1));
                break;
            }
        }
    }

    @Override
    public T removeRandom() {
        Random gen = new Random();
        T capture = (T)basis[gen.nextInt(bagSize-1)];
        remove(capture);
        return capture;
    }

    @Override
    public boolean contains(T element) {
        for(int index = 0; index < itemCount; index++) {
            if (basis[index] == element) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() { return bagSize == 0; }

    @Override
    public int size() { return this.itemCount; }

    private void expand(){
        bagSize += bagSize >> 1;
        basis = Arrays.copyOf(basis, bagSize);
    }

}
