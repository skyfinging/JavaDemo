package demo.service;

import demo.rmi.IRmiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class RmiClient {

    @Resource
    IRmiService hello;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String abc) {
        try {
            return hello.getMsg(abc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Bean
    @DependsOn("IHelloService")
    public RmiProxyFactoryBean hello(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IRmiService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://10.10.107.43:4099/IHello");
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
        return rmiProxyFactoryBean;
    }
}
