package base;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // ACCESS OPERATIONS 1
        System.out.println(">>ACCESS OPERATIONS 1");

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

        // Should be sampleSize1
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

        System.out.println(">>Test contains(T):");
        test2.contains(getRandomInt(sampleSize2));

        System.out.println(">>Test remove(T):");
        test2.remove(getRandomInt(sampleSize2));

        System.out.println(">>Test removeRandom():");
        test2.removeRandom();

        // ACCESS OPERATIONS 2
        System.out.println(">>ACCESS OPERATIONS 2");

        /* Test collections (matching types)
        - Test union(BagCollection<T>) on empty collection
        - Test union(BagCollection<T>) on non-empty collection
        - Test addAll(BagCollection<T>)
        - Test equals(BagCollection<T>) on matching collection
        - Test equals(BagCollection<T>) on non-matching collection
        - Test equals(BagCollection<T>) on matching but disparate collection
         */

        int sampleSize3 = 5;

        // Original
        BagCollection<Integer> test3 = new BagCollection<Integer>(sampleSize3);

        // Add some numbers
        for (int index = 0; index < sampleSize3; index++) {
            // Use wider bounds if using a small sample set
            test3.add(getRandomInt(100));
        }

        // Union an empty instance to create a new collection (Simulates a copy operation)
        System.out.println(">>Clone via union(BagCollection<T>) with empty set:");
        BagCollection<Integer> test4 = test3.union(new BagCollection<Integer>(0));
        System.out.println("Result= " + test4.toString());

        // Union the copy with original (Simulates a doubling of the collection)
        System.out.println(">>Double via union(BagCollection<T>) with Clone:");
        BagCollection<Integer> test5 = test3.union(test4);
        System.out.println("Result= " + test5.toString());

        // Add together the copy and the original. (Make the original a second double by adding the clone to it)
        // This instance will also expand on demand making its max differ from the union result.
        System.out.println(">>Double via addAll(BagCollection<T>): (Original + Clone)");
        test3.addAll(test4);
        System.out.println("Result= " + test3.toString());

        System.out.println(">>Compare of two Doubles via equals(BagCollection<T>):");
        System.out.println("Result= " + test3.equals(test5));

        System.out.println(">>Compare of original Clone and Double via equals(BagCollection<T>):");
        System.out.println("Result= " + test3.equals(test4));

        System.out.println(">>Modify Clone and compare again via equals(BagCollection<T>):");
        test3.removeRandom();
        test3.add(1234567890);
        System.out.println("Result= " + test3.equals(test5));
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
        String input = "The five boxing wizards jump quickly.";

        Random gen = new Random();
        int start = gen.nextInt(input.length());
        int end = start + gen.nextInt(input.length() - start);
        return input.substring(start, end);
    }
}
