package demo.iterator;


public class NullIterator  implements MyIterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

}
