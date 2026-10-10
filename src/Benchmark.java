import java.util.*;

public class Benchmark {

    static volatile int check;

    static long[] testBST(List<Integer> keys) {
        BST tree = new BST();

        long start = System.nanoTime();

        for (int key : keys) {
            tree.insert(key);
        }

        long afterInsert = System.nanoTime();

        int found = 0;
        for (int key : keys) {
            if (tree.search(key)) {
                found++;
            }
        }

        long afterSearch = System.nanoTime();

        for (int key : keys) {
            tree.delete(key);
        }

        long afterDelete = System.nanoTime();

        for (int key : keys) {
            if (tree.search(key)) {
                throw new AssertionError(
                        "BST delete failed for key: " + key
                );
            }
        }

        if (tree.root != null) {
            throw new AssertionError("BST is not empty");
        }

        if (found != keys.size()) {
            throw new AssertionError("BST search failed");
        }

        check = found;

        return new long[] {
                afterInsert - start,
                afterSearch - afterInsert,
                afterDelete - afterSearch
        };
    }

    static long[] testHashTable(List<Integer> keys) {
        HashTable table = new HashTable(16);

        long start = System.nanoTime();

        for (int key : keys) {
            table.insert(key);
        }

        long afterInsert = System.nanoTime();

        int found = 0;
        for (int key : keys) {
            if (table.search(key)) {
                found++;
            }
        }

        long afterSearch = System.nanoTime();

        for (int key : keys) {
            table.delete(key);
        }

        long afterDelete = System.nanoTime();

        if (found != keys.size() || table.size() != 0) {
            throw new AssertionError("Hash Table failed");
        }

        check = found;

        return new long[] {
                afterInsert - start,
                afterSearch - afterInsert,
                afterDelete - afterSearch
        };
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000};

        int warmup = 5;
        int runs = 20;

        System.out.println(
                "N | Structure | Insert(ms) | Search(ms) | Delete(ms)"
        );

        for (int n : sizes) {

            long[] bstTotal = new long[3];
            long[] hashTotal = new long[3];

            for (int run = 0; run < warmup + runs; run++) {

                List<Integer> keys = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    keys.add(i);
                }

                Collections.shuffle(keys, new Random(42 + run));

                long[] bst = testBST(keys);
                long[] hash = testHashTable(keys);

                if (run >= warmup) {
                    for (int j = 0; j < 3; j++) {
                        bstTotal[j] += bst[j];
                        hashTotal[j] += hash[j];
                    }
                }
            }

            printResult(n, "BST", bstTotal, runs);
            printResult(n, "HashTable", hashTotal, runs);
        }
    }

    static void printResult(
            int n, String name, long[] times, int runs
    ) {
        System.out.printf(
                Locale.US,
                "%d | %s | %.4f | %.4f | %.4f%n",
                n,
                name,
                times[0] / (runs * 1_000_000.0),
                times[1] / (runs * 1_000_000.0),
                times[2] / (runs * 1_000_000.0)
        );
    }
}