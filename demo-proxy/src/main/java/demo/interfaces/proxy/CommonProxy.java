package demo.interfaces.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CommonProxy  implements InvocationHandler {
    private Object proxyObject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理");
        Object result = null;
        result = method.invoke(proxyObject, args);
        return result;
    }

    public <T> T newProxyInstance(Object proxyObject){
        this.proxyObject = proxyObject;
        return (T)Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), proxyObject.getClass().getInterfaces(), this);
    }
}
