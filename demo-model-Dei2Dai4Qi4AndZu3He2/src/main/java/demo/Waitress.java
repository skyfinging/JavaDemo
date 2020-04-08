package demo;

import demo.iterator.CompositeIterator;
import demo.menu.MenuComponent;

import java.util.Iterator;

public class Waitress {
    final MenuComponent menuComponent;


    public Waitress(MenuComponent menuComponent) {
        this.menuComponent = menuComponent;
    }

    public void printMenu(){
        menuComponent.print("");
    }

    public void printVegetarianMenu(){
//        Iterator iterator = menuComponent.createIterator();
        Iterator iterator = new CompositeIterator("",menuComponent.createIterator());
        System.out.println("\nVEGETARIAN MENU\n----");
        while(iterator.hasNext()){
            MenuComponent menuComponent = (MenuComponent) iterator.next();
            if(menuComponent.isVegetarian()){
                menuComponent.print("");
            }
        }
    }
}
