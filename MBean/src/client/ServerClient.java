package client;

import mbean.ServerInfoMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class ServerClient {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);

        MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        ObjectName objectName = new ObjectName("serverInfoMBean:name=serverInfo");

       // mBeanServerConnection.setAttribute(objectName, new Attribute("Abc", "10"));
        int abc = (int) mBeanServerConnection.getAttribute(objectName, "Abc");
        System.out.println("getAbc() = " + abc);

        ServerInfoMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, objectName, ServerInfoMBean.class, false);
        int index = proxy.getIndex("index4");
        System.out.println("index4="+index);
        proxy.getExecutedSqlCmdCount();
        proxy.print();
        mBeanServerConnection.invoke(objectName, "abc", null, null);
    }
}
