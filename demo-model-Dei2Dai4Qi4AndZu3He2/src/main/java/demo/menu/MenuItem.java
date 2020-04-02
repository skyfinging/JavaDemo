package demo.menu;


import demo.iterator.MyIterator;
import demo.iterator.NullIterator;

public class MenuItem extends MenuComponent {
    final String name;
    final String description;
    final boolean vegetarian;
    final double price;
    String prefx="";

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public void print(String prefx) {
        System.out.print(prefx+this.prefx+getName());
        if(isVegetarian()){
            System.out.print("(V)");
        }
        System.out.println();
    }

    @Override
    public void setPrefx(String name) {
        this.prefx = name;
    }

    @Override
    public MyIterator createIterator() {
        return new NullIterator();
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean isVegetarian(){
        return vegetarian;
    }

    public double getPrice(){
        return price;
    }
}
