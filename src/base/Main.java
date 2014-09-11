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
        System.out.println(">>The current state is isEmpty=" + test1.isEmpty());

        // Add beyond 10 to test auto-expansion
        for (int index = 0; index < sampleSize1; index++) {
            test1.add(getRandomText());
        }

        // Post-Add
        System.out.println(">>The current state is isEmpty=" + test1.isEmpty());

        // Should be 10 + sampleSize1
        System.out.println(">>The current number of items is " + test1.size());

        /* Test with numbers
        - Test constructor w/parameter
        - Test contains(T)
        - Test remove(T)
        - Test removeRandom()
         */

        int sampleSize2 = 15;

        BagCollection<Integer> test2 = new BagCollection<Integer>(sampleSize2);

        // Populate bag to capacity
        for (int index = 0; index < test2.getMax(); index++) {
            test2.add(getRandomInt(sampleSize2));
        }

        test2.contains(getRandomInt(sampleSize2));
        test2.remove(getRandomInt(sampleSize2));
        test2.removeRandom();

        // ACCESS OPERATIONS 2

        /* Test collections (matching types)
        - Test union() on empty collection
        - Test union() on non-empty collection
        - Test addAll()
        - Test equals() on matching collection
        - Test equals() on non-matching collection
         */

        int sampleSize3 = 2;

        // Original
        BagCollection<Integer> test3 = new BagCollection<Integer>(sampleSize3);

        // Add some numbers
        for (int index = 0; index < sampleSize3; index++) {
            // Use wider bounds if using a small sample set
            test3.add(getRandomInt(100));
        }

        // Union an empty instance to create a new collection (Simulates a copy operation)
        BagCollection<Integer> test4 = test3.union(new BagCollection<Integer>(0));

        // Union the copy with original (Simulates an doubling of the collection)
        BagCollection<Integer> test5 = test3.union(test4);

        // Add together the copy and the original. (Make the original a second double by adding the clone to it)
        // This instance will also expand on demand making its max differ from the union result.
        test3.addAll(test4);

        // Compare the two doubles
        System.out.println(test3.equals(test5));

        // Compare the clone and one of the doubles
        System.out.println(test3.equals(test4));
    }

    static int getRandomInt(int bounds) {
        Random gen = new Random();
        try {
            if (bounds > 0) {
                return gen.nextInt(bounds);
            } else {
                return gen.nextInt();
            }
        } finally {
            gen=null;
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
