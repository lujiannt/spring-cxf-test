package rs.spring;

import rs.domain.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogServiceImpl implements DogService {
    @Override
    public Dog findDogByName2(String dogName) {
        System.out.println("dog2, dogName = " + dogName);
        Dog dog = new Dog();
        dog.setName("小白");
        dog.setType(2);
        return dog;
    }

    @Override
    public List<Dog> findDogs() {
        List<Dog> list = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setName("小白");
        dog1.setType(2);
        list.add(dog1);

        Dog dog2 = new Dog();
        dog2.setName("小白");
        dog2.setType(2);
        list.add(dog2);
        return list;
    }

    @Override
    public void saveDog(Dog dog) {
        System.out.println("start save dog");
        System.out.println("dog's name is " + dog.getName());
        System.out.println("save dog success");
    }
}
