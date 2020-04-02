package demo.interfaces.impl;

import demo.interfaces.IRun;
import demo.interfaces.IUserManager;

public class UserManagerImpl implements IUserManager, IRun {
    @Override
    public void addUser(String userName) {
        System.out.println("addUser: "+userName);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("delUser: "+userName);
    }

    @Override
    public void run() {
        System.out.println("run。");
    }
}
