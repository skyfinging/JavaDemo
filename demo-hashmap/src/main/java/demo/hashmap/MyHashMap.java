package demo.hashmap;

public class MyHashMap<K,V> {
    private int defaultSize = 16;
    private Entry<K,V>[] arr;
    private int mount = 0;
    private float loadFactor = 0.75f;
    private int size = 0;

    public MyHashMap(){
        arr = new Entry[defaultSize];
    }

    public void put(K key, V value){
        Entry<K,V> entry = new Entry(key,value);
        int hashcode = entry.getHashcode();
        int index = getIndex(hashcode);
        putEntry(arr, index,entry, false);
        System.out.println("put:"+key+",size:"+this.size+",mount:"+this.mount+",length:"+this.arr.length);
    }

    public static int getHashcode(Object key){
        if(key==null)
            return 0;
        return key.hashCode();
    }

    private int putEntry(Entry<K,V>[] arr, int index, Entry<K,V> entry, boolean resizeFlag){
        for(Entry tmp = arr[index];tmp!=null;tmp=tmp.getNext()){
            if(tmp.isEqualsKey(entry.getKey())){ //已经存在有相同的key
                tmp.setValue(entry.getValue());
                return 0;
            }else if(tmp.getNext()==null){  //hashcode相同，但是key不同
                tmp.setNext(entry);
                this.size++;
                return 0;
            }
        }
        arr[index] = entry;//该位置没有保存元素
        this.size++;
        if(resizeFlag==false) {
            mount++;
            if (mount >= arr.length * loadFactor) {
                resize(arr.length * 2);
            }
        }
        return 1;
    }

    private void resize(int newSize){
        Entry<K,V>[] newArr = new Entry[newSize];
        int mountNew = 0;
        int oldSize = size;
        for(int i=0;i<arr.length;i++){
            Entry<K,V> entry = arr[i];
            if(entry==null)
                continue;
            int mountTmp = rePutEntry(entry, newArr);
            mountNew += mountTmp;
        }
        this.arr = newArr;
        this.mount = mountNew;
        this.size = oldSize;
    }

    private int rePutEntry(Entry<K,V> entry, Entry<K,V>[] newArr){
        int hashcode = getHashcode(entry.getKey());
        int index = getIndex(hashcode);
        int addMount = putEntry(newArr,index,entry,true);
        if(entry.getNext()!=null){
            Entry<K,V> nextEntry = entry.getNext();
            entry.setNext(null);
            return addMount+rePutEntry(nextEntry, newArr);
        }
        return addMount;
    }

    public V get(K key){
        int hashcode = getHashcode(key);
        int index = getIndex(hashcode);
        Entry<K,V> entry = getEntry(index, key);
        if(entry==null)
            return null;
        return entry.getValue();
    }

    private Entry<K,V> getEntry(int index, K key){
        for(Entry tmp = arr[index];tmp!=null;tmp=tmp.getNext()){
            if(tmp.isEqualsKey(key)){ //找到该key
                return tmp;
            }else if(tmp.getNext()==null){  //没有该key
                return null;
            }
        }
        return arr[index];
    }

    private int getIndex(int hashcode){
        int length = arr.length;
        int index = hashcode % length;
        return index;
    }
}
