package tr.com.app.scheduler.model;

import org.hibernate.annotations.GenericGenerator;
import tr.com.app.scheduler.controller.PresentationType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PRESENTATION")
public class Presentation {

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(
            name = "id-generator",
            strategy = "tr.com.app.cpa.util.IdGenerator")
    private Long id;

    private String name;
    private Integer timeAsMinute;
    private PresentationType type;

    public Presentation() {
    }

    public Presentation(Long id, String name, Integer timeAsMinute, PresentationType type) {
        this.id = id;
        this.name = name;
        this.timeAsMinute = timeAsMinute;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeAsMinute() {
        return timeAsMinute;
    }

    public void setTimeAsMinute(Integer timeAsMinute) {
        this.timeAsMinute = timeAsMinute;
    }

    public PresentationType getType() {
        return type;
    }

    public void setType(PresentationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presentation etkinlik = (Presentation) o;
        return Objects.equals(id, etkinlik.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
