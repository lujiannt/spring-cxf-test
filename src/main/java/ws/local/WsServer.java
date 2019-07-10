package ws.local;

import javax.xml.ws.Endpoint;

public class WsServer {
    public static void main(String[] args) {
        // 1.服务实现类对象
        UserService userService = new UserServiceImpl();
        // 2.发布服务地址
        String address = "http://localhost:9999/userService";
        // 3.发布服务
        Endpoint.publish(address, userService);
        System.out.println("服务开启了....");
    }
}
