package demo;

import demo.menu.Menu;
import demo.menu.MenuComponent;
import demo.menu.MenuItem;

public class Main {
    public static void main(String[] args)  {
        MenuComponent breakfastMenu = new Menu("早餐菜单","早餐");
        MenuComponent lunchMenu = new Menu("午餐菜单","午餐");
        MenuComponent dinnerMenu = new Menu("晚餐菜单", "晚餐");
        MenuComponent addMenu = new Menu("加料","晚餐加料");
        MenuComponent addMenu2 = new Menu("加料2","晚餐加料2");

        MenuComponent allMenus = new Menu("ALL MENUS","All menus combined");

        allMenus.add(breakfastMenu);
        allMenus.add(lunchMenu);
        allMenus.add(dinnerMenu);

        breakfastMenu.add(new MenuItem("豆浆","像水一样",false,0));
        breakfastMenu.add(new MenuItem("馒头","像石头一样",false,0));
        breakfastMenu.add(new MenuItem("玉米","还行",true,0));

        lunchMenu.add(new MenuItem("烧腊","手撕鸡",false, 15));
        lunchMenu.add(new MenuItem("荷叶饭","荷叶饭",false, 15));

        dinnerMenu.add(new MenuItem("面", "食堂的面",false,10));
        dinnerMenu.add(new MenuItem("粉", "食堂的粉",false,10));
        addMenu.add(new MenuItem("萝卜","去晚了就没有了",true,0));
        addMenu.add(new MenuItem("咸菜","不咸",true,0));
        addMenu2.add(new MenuItem("萝卜2","去晚了就没有了",true,0));
        addMenu2.add(new MenuItem("咸菜2","不咸",true,0));
        addMenu.add(addMenu2);
        dinnerMenu.add(addMenu);

        Waitress waitress = new Waitress(allMenus);
        //打印完整菜单
        waitress.printMenu();

        //只打印素食菜单
        waitress.printVegetarianMenu();
    }
}
