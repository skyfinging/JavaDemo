package demo.hashmap;

public class Entry<K,V> {
    private final K key;
    private V value;
    private Entry<K,V> next;
    private int hashcode;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        this.hashcode = MyHashMap.getHashcode(key);
    }

    public boolean isEqualsKey(K key){
        if(this.key==null){
            if(key==null)
                return true;
            else
                return false;
        }
        if(this.key.equals(key))
            return true;
        else
            return false;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }

    public int getHashcode(){
        return this.hashcode;
    }

    public void setNext(Entry<K,V> next){
        this.next = next;
    }

    public Entry<K,V> getNext(){
        return this.next;
    }

}
