package demo;

import demo.rmi.IHello;
import demo.rmiimpl.HelloImpl;

public class ServerMain {
    public static void main(String[] args) {
        try {
            IHello hello = new HelloImpl();
            java.rmi.registry.LocateRegistry.createRegistry(3099);
            java.rmi.Naming.rebind("rmi://10.10.107.21:3099/hello", hello);
            System.out.print("Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
