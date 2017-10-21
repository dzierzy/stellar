package pl.com.sages.stellar.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Constellation {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "constellation")
    private transient List<Star> stars = new ArrayList<>();

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


    public List<Star> getStars() {
        return stars;
    }

    public void addStar(Star star) {
        this.stars.add(star);
        star.setConstellation(this);
    }
}
