package ws.local;

import javax.jws.WebService;

// 注解设置 endPointInterface 接口服务完整类名， servicename 服务名称
@WebService(endpointInterface = "ws.local.UserService", serviceName = "userService")
public class UserServiceImpl implements UserService {

    @Override
    public String sayName(String name) {
        System.out.println("我是sayName方法");
        return name;
    }
}
