package demo.iterator;

import demo.menu.Menu;
import demo.menu.MenuComponent;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements MyIterator {
    String name;
    Stack<Iterator> stack = new Stack();

    public CompositeIterator(String name, Iterator iterator){
        this.name = name;
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty())
            return false;
        Iterator iterator = stack.peek();
        if(!iterator.hasNext()){
            stack.pop();
            return hasNext();
        }else
            return true;
    }

    @Override
    public Object next() {
        if(hasNext()){
            Iterator iterator = stack.peek();
            MenuComponent menuComponent = (MenuComponent) iterator.next();
            if(iterator instanceof MyIterator)
                menuComponent.setPrefx(((MyIterator) iterator).getName()+".");
            else
                menuComponent.setPrefx(name+".");
            if(menuComponent instanceof Menu){
                stack.push(menuComponent.createIterator());
            }
            return menuComponent;
        }else
            return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
