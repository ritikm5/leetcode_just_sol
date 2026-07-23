import java.util.LinkedList;

class MyHashMap {

    private static final int SIZE = 769; // Prime number
    private LinkedList<Node>[] buckets;

    private static class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);

        for (Node node : buckets[index]) {
            if (node.key == key) {
                node.value = value;
                return;
            }
        }

        buckets[index].add(new Node(key, value));
    }

    public int get(int key) {
        int index = hash(key);

        for (Node node : buckets[index]) {
            if (node.key == key) {
                return node.value;
            }
        }

        return -1;
    }

    public void remove(int key) {
        int index = hash(key);

        buckets[index].removeIf(node -> node.key == key);
    }
}

