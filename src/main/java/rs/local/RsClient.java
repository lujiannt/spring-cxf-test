package rs.local;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import rs.domain.Dog;
import utils.NetRequestUtil;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.LinkedHashMap;

public class RsClient {
    public static void main(String[] args) {
        // 参数说明：
        // create是创建一个连接，给定一个访问地址
        // accept是接收一个指定类型的数据，但是这个类型必须是服务接口中指定的返回数据类型
        // getCollection是获取所有的对象的方法，如果是一个对象，就是要get，这个根据实际来定
        // type是发送数据的格式，这个必须是服务端接口定义的接收的数据类型
//        Collection<? extends Dog> collection = WebClient.create("http://localhost:10086/dogService/dog3")
//                .accept(MediaType.APPLICATION_XML).getCollection(Dog.class);
//        System.out.println(collection);

        Dog dog = new Dog();
        dog.setName("zcj");
        WebClient.create("http://localhost:10086/dogService/dog4")
                .type(MediaType.APPLICATION_JSON).post(dog);
    }

    /*----------------------------------- 插入 ---------------------------------*/

    /**
     * xml
     */
    @Test
    public void testXml_save() {
        // type是发送数据的格式，这个必须是服务端接口定义的接收的数据类型
        Dog dog = new Dog();
        dog.setName("zcj");
        WebClient.create("http://localhost:10086/dogService/saveDog")
                .type(MediaType.APPLICATION_XML).post(dog);
    }

    /**
     * json 【注意】json格式需要额外导入包
     */
    @Test
    public void testJson_save() {
        // type是发送数据的格式，这个必须是服务端接口定义的接收的数据类型
        Dog dog = new Dog();
        dog.setName("zcj");
        WebClient.create("http://localhost:10086/dogService/saveDog")
                .type(MediaType.APPLICATION_JSON).post(dog);
    }

    /*----------------------------------- 查询 ---------------------------------*/

    /**
     * 查询单个GET 【注意】json格式需要额外导入包
     */
    @Test
    public void testXml_get1() {
        // accept是接收一个指定类型的数据，但是这个类型必须是服务接口中指定的返回数据类型
        WebClient webClient = WebClient.create("http://localhost:10086/dogService/dog1/1314")
                .accept(MediaType.APPLICATION_JSON);
        Dog dog = webClient.get(Dog.class);
        System.out.println(dog.toString());
    }

    /**
     * 查询单个Post,这里用postMan访问【注意】json格式需要额外导入包
     */
    @Test
    public void testXml_post1() {
        // accept是接收一个指定类型的数据，但是这个类型必须是服务接口中指定的返回数据类型
        WebClient webClient = WebClient.create("http://localhost:10086/dogService/dog2")
                .accept(MediaType.APPLICATION_JSON);

        //TODO 这里没有找到用webClient怎么post传自定义参数，只能像上面那样传对象
        //TODO 所以这里暂时用HttpClient实现访问
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("name", "zcj");
        NetRequestUtil.sendPostRequest("http://localhost:10086/dogService/dog2", params);
    }

    /**
     * 查询多个
     */
    @Test
    public void testXml_getAll() {
        // accept是接收一个指定类型的数据，但是这个类型必须是服务接口中指定的返回数据类型
        WebClient webClient = WebClient.create("http://localhost:10086/dogService/dogs")
                .accept(MediaType.APPLICATION_JSON);
        Collection<? extends Dog> collection = webClient.getCollection(Dog.class);
        System.out.println(collection);
    }
}
