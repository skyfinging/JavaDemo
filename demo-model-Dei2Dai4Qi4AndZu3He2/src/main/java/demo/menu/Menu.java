package demo.menu;
import demo.iterator.MyIterator;
import demo.iterator.NameIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    final String name;
    final String description;
    String prefx="";

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public MyIterator createIterator() {
//        return new CompositeIterator(prefx+name,menuComponents.iterator());
        return new NameIterator(prefx+name,menuComponents.iterator());
    }

    @Override
    public void add(MenuComponent menuComponent){
        menuComponents.add(menuComponent);
    }

    @Override
    public void print(String prefx) {
        System.out.println(prefx+this.prefx+getName()+","+getDescription());
        System.out.println(prefx+"-------------------------");
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while(iterator.hasNext()){
            MenuComponent menuComponent = iterator.next();
            menuComponent.print(prefx+"\t");
        }
    }

    @Override
    public void setPrefx(String name) {
        this.prefx = name;
    }

    @Override
    public boolean isVegetarian() {
        return false;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }


}
