import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private int size = 0;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private ArrayList<LinkedList<Integer>> buckets;

    public HashTable(int capacity) {
        buckets = createBuckets(capacity);
    }

    private ArrayList<LinkedList<Integer>> createBuckets(int capacity) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            result.add(new LinkedList<>());
        }
        return result;
    }

    private int hash(int key) {
        return key % buckets.size();
    }

    public void insert(int key) {
        int i = hash(key);
        LinkedList<Integer> chain = buckets.get(i);

        if (chain.contains(key)) {
            return;
        }

        chain.addFirst(key);
        size++;

        if (loadFactor() > MAX_LOAD_FACTOR) {
            resize();
        }
    }

    public boolean search(int key) {
        int i = hash(key);
        return buckets.get(i).contains(key);
    }

    public boolean delete(int key) {
        int i = hash(key);

        boolean removed =
                buckets.get(i).remove(Integer.valueOf(key));

        if (removed) {
            size--;
        }

        return removed;
    }

    private void resize() {
        int newCapacity = buckets.size() * 2;

        ArrayList<LinkedList<Integer>> newBuckets =
                createBuckets(newCapacity);

        for (LinkedList<Integer> chain : buckets) {
            for (int key : chain) {
                int newIndex = key % newCapacity;
                newBuckets.get(newIndex).addFirst(key);
            }
        }
        buckets = newBuckets;
    }

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.size();
    }
}