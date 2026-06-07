class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            keys = new HashSet<>();
        }
    }

    private Map<String, Node> map;
    private Node head;
    private Node tail;

    public AllOne() {
        map = new HashMap<>();

        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {

        if (!map.containsKey(key)) {

            Node first = head.next;

            if (first == tail || first.count != 1) {
                Node newNode = new Node(1);
                insertAfter(head, newNode);
            }

            head.next.keys.add(key);
            map.put(key, head.next);

        } else {

            Node cur = map.get(key);
            Node next = cur.next;

            if (next == tail || next.count != cur.count + 1) {
                Node newNode = new Node(cur.count + 1);
                insertAfter(cur, newNode);
            }

            cur.next.keys.add(key);
            map.put(key, cur.next);

            cur.keys.remove(key);

            if (cur.keys.isEmpty()) {
                remove(cur);
            }
        }
    }

    public void dec(String key) {

        Node cur = map.get(key);

        if (cur.count == 1) {

            map.remove(key);

            cur.keys.remove(key);

            if (cur.keys.isEmpty()) {
                remove(cur);
            }

        } else {

            Node prev = cur.prev;

            if (prev == head || prev.count != cur.count - 1) {
                Node newNode = new Node(cur.count - 1);
                insertAfter(prev, newNode);
            }

            cur.prev.keys.add(key);
            map.put(key, cur.prev);

            cur.keys.remove(key);

            if (cur.keys.isEmpty()) {
                remove(cur);
            }
        }
    }

    public String getMaxKey() {

        if (tail.prev == head) {
            return "";
        }

        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {

        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    private void insertAfter(Node prev, Node node) {

        node.next = prev.next;
        node.prev = prev;

        prev.next.prev = node;
        prev.next = node;
    }

    private void remove(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
