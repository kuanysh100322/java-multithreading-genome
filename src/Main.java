
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final char[] GENES = {'A', 'T', 'G', 'C'};
    private static final int SEQUENCE_LENGTH = 10;
    private static final int TOTAL_SEQUENCES = 100;

    public static void main(String[] args) {

        long startSingle = System.currentTimeMillis();

        List<String> singleResult = generateSequences(TOTAL_SEQUENCES);

        long endSingle = System.currentTimeMillis();

        System.out.println("Single thread result:");
        printSome(singleResult);
        System.out.println("Time: " + (endSingle - startSingle) + " ms");

        System.out.println("----------------------------------");

        long startMulti = System.currentTimeMillis();

        List<String> multiResult = generateWithThreads(5);

        long endMulti = System.currentTimeMillis();

        System.out.println("Multi thread result:");
        printSome(multiResult);
        System.out.println("Time: " + (endMulti - startMulti) + " ms");
    }

    public static List<String> generateSequences(int count) {
        List<String> result = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            result.add(generateOne(random));
        }

        return result;
    }

    public static String generateOne(Random random) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            sb.append(GENES[random.nextInt(GENES.length)]);
        }

        return sb.toString();
    }
    public static List<String> generateWithThreads(int threadCount) {

        List<String> result = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        int perThread = TOTAL_SEQUENCES / threadCount;

        for (int i = 0; i < threadCount; i++) {

            Thread t = new Thread(() -> {
                List<String> local = generateSequences(perThread);

                synchronized (result) {
                    result.addAll(local);
                }
            });

            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void printSome(List<String> list) {
        for (int i = 0; i < 5 && i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}