package demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MainM {
    static interface  It{
        void sout();
    }
    static class OOMTest implements It{
        @Override
        public void sout() {
        }
    }
    // -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=10m
    public static void main(String[] args) {
        int i=0;
        It it = new OOMTest();
        try {
            while (true) {
                i++;
                //动态代理不会产生Metaspace溢出，因为用了对classLoader和Interfaces作了缓存
//                int finalI = i;
//                It proxy = (It) Proxy.newProxyInstance(MainM.class.getClassLoader(),
//                        it.getClass().getInterfaces(), new InvocationHandler() {
//                            @Override
//                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                                System.out.println(finalI);
//                                return method.invoke(it, args);
//                            }
//                        });
//                 proxy.sout();
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return method.invoke(it,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("i="+i);
        }
    }
}
