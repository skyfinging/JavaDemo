package demo.iterator;

import java.util.Iterator;

public class NameIterator implements MyIterator {
    Iterator iterator;
    String name;
    public NameIterator(String name, Iterator iterator){
        this.name = name;
        this.iterator = iterator;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        return iterator.next();
    }
}
