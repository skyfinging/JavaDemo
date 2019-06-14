package demo.rmiimpl;

import demo.rmi.IHello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements IHello {

    private static final long serialVersionUID = -889648649417131285L;

    public HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name + " ^_^ ";
    }
}
