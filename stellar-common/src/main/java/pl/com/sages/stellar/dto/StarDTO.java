package pl.com.sages.stellar.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StarDTO {

    private String id;

    private String name;

    public StarDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StarDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
