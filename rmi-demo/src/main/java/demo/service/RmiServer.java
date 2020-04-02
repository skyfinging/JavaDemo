package demo.service;

import demo.rmi.HelloRmiService;
import demo.rmi.IRmiService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component
public class RmiServer {

    @Bean(name="IHelloService")
    public RmiServiceExporter getAccountService() throws RemoteException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("IHello");
        HelloRmiService helloRmiService = new HelloRmiService();
        helloRmiService.setA(10);
        rmiServiceExporter.setService(helloRmiService);
        rmiServiceExporter.setServiceInterface(IRmiService.class);
        rmiServiceExporter.setRegistryPort(4099);
        return rmiServiceExporter;
    }
}
