import java.util.HashMap;
import java.util.Map;

class LRU {

    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    Map<Integer, Node> map = new HashMap<>();

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node data = map.get(key);
            remove(data);
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    public void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node data = map.get(key);
            map.remove(key);
            remove(data);
            insert(data);
            return data.value;
        }

        return -1;
    }

    public void printLRU() {
        Node node = head;
        while (node.next != null) {
            System.out.print(node.key+" ");
            node = node.next;
        }
    }
}

class Node {

    Node prev, next;
    int key, value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }


}

public class LRUCache {

    public static void main(String []args) {

        LRU lru = new LRU(3);

        lru.put(1,1);
        lru.put(2,1);
        lru.put(3,1);

        System.out.println("data is added");
        System.out.println(lru);

        lru.put(4,1);

        lru.printLRU();


    }
}
