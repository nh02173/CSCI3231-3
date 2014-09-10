package base;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // ACCESS OPERATIONS 1

        /* Test with strings
        - Test constructor w/default
        - Test isEmpty
        - Test add(T)
        - Test expand
        - Test size()
         */

        int sampleSize1 = 30;

        // Default Size = 10
        BagCollection<String> test1 = new BagCollection<String>();

        // Pre-Add
        System.out.println("-The current state is isEmpty=" + test1.isEmpty());

        // Add beyond 10 to test auto-expansion
        for (int index = 0; index < sampleSize1; index++) {
            test1.add(getRandomText());
        }

        // Post-Add
        System.out.println("-The current state is isEmpty=" + test1.isEmpty());

        // Should be sampleSize1
        System.out.println("-The current number of items is " + test1.size());

        /* Test with numbers
        - Test constructor w/parameter
        - Test getCapacity()
        - Test contains(T)
        - Test remove(T)
        - Test removeRandom()
         */

        int sampleSize2 = 15;

        BagCollection<Integer> test2 = new BagCollection<Integer>(sampleSize2);

        // Populate bag to capacity
        for (int index = 0; index < test2.getCapacity(); index++) {
            test2.add(getRandomInt(sampleSize2));
        }

        test2.contains(getRandomInt(sampleSize2));
        test2.remove(getRandomInt(sampleSize2));
        test2.removeRandom();
    }

    static int getRandomInt(int bounds) {
        Random gen = new Random();
        if (bounds > 0) {
            return gen.nextInt(bounds);
        } else {
            return gen.nextInt();
        }
    }

    static String getRandomText() {
        String input = "The quick brown fox jumps over the lazy dog.";

        Random gen = new Random();
        int start = gen.nextInt(input.length());
        int end = start + gen.nextInt(input.length() - start);
        return input.substring(start, end);
    }
}
