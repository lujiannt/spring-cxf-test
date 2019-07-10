package rs.local;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import rs.domain.Dog;
import rs.local.service.DogService;
import rs.local.service.DogServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过手动发布服务
 */
public class RsServer {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        DogService dogService = new DogServiceImpl();
        list.add(dogService);

        //手动创建服务
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        //设置实体类
        jaxrsServerFactoryBean.setResourceClasses(Dog.class);
        //设置访问地址
        jaxrsServerFactoryBean.setAddress("http://localhost:10086");
        //设置访问接口
        jaxrsServerFactoryBean.setServiceBeans(list);

        //发布服务
        jaxrsServerFactoryBean.create();
        System.out.println("服务开启。。。");
    }
}
