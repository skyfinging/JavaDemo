package demo.rmi;

import java.io.Serializable;
import java.rmi.Remote;

public interface IHello extends Remote, Serializable {
    String sayHello(String name) throws java.rmi.RemoteException;
}
