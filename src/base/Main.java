package base;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

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

        // Should be 10 + sampleSize1
        System.out.println("-The current Bag size is " + test1.size());

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
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc gravida in erat in condimentum." +
                " Aliquam viverra tellus sem, non suscipit nunc facilisis et. Quisque tristique egestas nulla, eu" +
                " varius arcu luctus at. Suspendisse vestibulum pharetra ligula, quis porta lorem vehicula id." +
                " Praesent fermentum euismod elementum. Fusce elementum pretium nunc a aliquam. Proin vel sapien quis" +
                " leo vestibulum convallis. Integer nisl lacus, tincidunt quis faucibus non, viverra cursus ante." +
                " Duis porttitor pretium viverra. Cras eget urna id dui auctor semper. Sed et orci vel erat viverra" +
                " finibus. Fusce est eros, dictum in cursus vel, tempus non libero. Phasellus commodo sem at.";

        Random gen = new Random();
        int start = gen.nextInt(input.length());
        int end = start + gen.nextInt(input.length() - start);
        return input.substring(start, end);
    }
}
