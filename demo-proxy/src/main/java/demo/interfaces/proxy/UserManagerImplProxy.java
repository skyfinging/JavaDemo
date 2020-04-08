package demo.interfaces.proxy;

import demo.interfaces.IUserManager;
import demo.interfaces.impl.UserManagerImpl;

public class UserManagerImplProxy implements IUserManager {

    private UserManagerImpl userManager;

    public UserManagerImplProxy(UserManagerImpl userManager){
        this.userManager = userManager;
    }

    @Override
    public void addUser(String userName) {
        System.out.println("代理addUser");
        userManager.addUser(userName);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("代理delUser");
        userManager.delUser(userName);
    }
}
