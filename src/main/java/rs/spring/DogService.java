package rs.spring;

import rs.domain.Dog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces({MediaType.APPLICATION_JSON})
public interface DogService {
    /*
     *   Path要删掉，与spring重复了
     *   与spring整合配置步骤：
     *               1.web.xml配置cxf-servlet 和 spring
     *               2.配置applicationContext.xml
     *               3.启动tomcat
     *
     * */

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
