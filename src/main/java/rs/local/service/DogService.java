package rs.local.service;

import rs.domain.Dog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/dogService")
@Produces({MediaType.APPLICATION_JSON})
public interface DogService {
    /*
    *   @Path 指定了服务访问资源路径，访问路径是类上的path+方法上的path；
        @Consumes 指定能够处理客户端传递过来数据格式，也就是说指定了客户端传递过来的数据格式；
        @Produces 指定能否生成哪种格式数据返回给客户端；
        @GET 查询 @PUT 修改 @POST 增加 @DELETE 删除；
        @PathParam("id")指定了拼接在访问路径上参数。
        @PathParam("id")指定了拼接在访问路径上参数。
    * */
    @GET
    @Path("/dog1/{id}")
    @Consumes({"application/xml", "application/json"})
    Dog findDogByName1(@PathParam("id") int id);

    @POST
    @Path("/dog2")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, "application/xml", "application/json", "multipart/form-data"})
    Dog findDogByName2(@FormParam("name") String dogName);

    @GET
    @Path("/dogs")
    @Consumes({"application/xml", "application/json"})
    List<Dog> findDogs();


    @POST
    @Path("/saveDog")
    @Consumes({"application/xml", "application/json"})
    void saveDog(Dog dog);
}
