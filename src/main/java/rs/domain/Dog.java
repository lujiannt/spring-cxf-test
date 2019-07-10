package rs.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dog")//用于该实体类和xml转换
public class Dog {
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
