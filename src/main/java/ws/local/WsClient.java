package ws.local;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WsClient {
    public static void main(String[] args) {
        // 编写客户端，调用WebService服务
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:9999/userService");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        // 日志打印设置，是可选设置
        jaxWsProxyFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
        jaxWsProxyFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        // 创建调用远程服务的代理对象
        UserService proxy = (UserService) jaxWsProxyFactoryBean.create();
        // 直接调用远程服务的方法
        System.out.println(proxy.sayName("ITCAST"));

    }
}
