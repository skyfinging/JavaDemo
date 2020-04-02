package demo;

import demo.interfaces.IRun;
import demo.interfaces.IUserManager;
import demo.interfaces.impl.UserManagerImpl;
import demo.interfaces.proxy.CommonProxy;
import demo.interfaces.proxy.UserManagerImplProxy;

public class Main {
    public static void main(String[] args) {
        IUserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("abc");
        userManager.delUser("abc");

        CommonProxy commonProxy = new CommonProxy();
        IUserManager userManager1 = commonProxy.newProxyInstance(new UserManagerImpl());
        userManager1.addUser("小明");
        userManager1.delUser("小明");
        IRun run = (IRun)userManager1;
        run.run();
    }
}
