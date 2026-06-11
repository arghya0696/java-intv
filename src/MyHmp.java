import java.util.LinkedList;

public class MyHmp<K,V> {

    private static class Node<K,V> {

        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    int N;
    LinkedList<Node<K,V>>[] buckets;


    public MyHmp(int n) {
        N = n;

        this.buckets = new LinkedList[N];
        for(int i=0; i<N;i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    int getBucketIndex(K key) {
        int hashCode = key.hashCode();

        return Math.abs(hashCode) % N;
    }

    int getDataIndex(K key, int bi) {

        LinkedList<Node<K,V>> bucket = buckets[bi];

        for(int i=0;i<bucket.size();i++) {
            if(bucket.get(i).key == key) {
                return i;
            }
        }
        return -1;
    }

    void put(K key, V value) {

        int bi = getBucketIndex(key);

        int di = getDataIndex(key, bi);

        if(di == -1) {
            buckets[bi].add(new Node(key, value));
        } else {
            buckets[bi].get(di).value = value;
        }
    }

    V get(K key) {
        int bi = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[bi];

        for (Node<K, V> kvNode : bucket) {
            if (kvNode.key == key)
                return kvNode.value;
        }
        return null;
    }

    void remove(K key) {
        int bi = getBucketIndex(key);

        LinkedList<Node<K, V>> bucket = buckets[bi];
        int removeIndex = -1;

        for(int i=0;i<bucket.size();i++) {
            if(bucket.get(i).key == key) {
                removeIndex = i;
                break;
            }
        }

        if(removeIndex == -1)
            return;

        bucket.remove(removeIndex);

    }

    void print() {
        for (int i=0;i<N;i++) {
            LinkedList<Node<K,V>> bucket = buckets[i];
            for (Node node : bucket) {
                System.out.print("key:" + node.key + " value:" + node.value);
                System.out.println();
            }
        }
    }
}
