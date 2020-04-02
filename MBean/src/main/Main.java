package main;

import listener.JackChangeNameListener;
import listener.JackGetNameFilter;
import listener.JackSetNameFilter;
import mbean.Jack;
import mbean.ServerInfo;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

public class Main {
    /**
     * 启动jconsole可调用serverInfoMbean中的方法
     * 调用Jack的setName方法，将会触发一次事件广播
     * @param args
     * @throws MalformedObjectNameException
     * @throws NotCompliantMBeanException
     * @throws InstanceAlreadyExistsException
     * @throws MBeanRegistrationException
     * @throws IOException
     */
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName serverInfoName = new ObjectName("serverInfoMBean:name=serverInfo");
        ServerInfo serverInfo = new ServerInfo("serverInfoMBean1");
        mBeanServer.registerMBean(serverInfo, serverInfoName);
        ObjectName serverInfoName2 = new ObjectName("serverInfoMBean2:name=serverInfo");
        ServerInfo serverInfo2 = new ServerInfo("serverInfoMBean2");
        mBeanServer.registerMBean(serverInfo2, serverInfoName2);


        ObjectName jackName = new ObjectName("jackMbean:name=jack");
        Jack jack = new Jack();
        mBeanServer.registerMBean(jack, jackName);
        JackChangeNameListener listener = new JackChangeNameListener();
        NotificationFilter setNameFilter = new JackSetNameFilter();
        NotificationFilter getNameFilter = new JackGetNameFilter();
        jack.addNotificationListener(listener, setNameFilter ,serverInfo);
        jack.addNotificationListener(listener, getNameFilter ,serverInfo2);

        startAgent(mBeanServer);

        try {
            Thread.sleep(6000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动监听，可不通过jconsole，而是通过client来调用MBean
     * @param mBeanServer
     * @throws IOException
     */
    private static void startAgent(MBeanServer mBeanServer) throws IOException {
        LocateRegistry.createRegistry(9999);
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
        jmxConnectorServer.start();
    }
}
