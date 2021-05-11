package tr.com.app.scheduler.model;

import org.hibernate.annotations.GenericGenerator;
import tr.com.app.scheduler.controller.PresentationType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * The type Presentation.
 */
@Entity
@Table(name = "PRESENTATION")
public class Presentation {

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(
            name = "id-generator",
            strategy = "tr.com.app.scheduler.util.IdGenerator")
    private Long id;

    private String name;
    private Integer timeAsMinute;
    private PresentationType type;

    /**
     * Instantiates a new Presentation.
     */
    public Presentation() {
    }

    /**
     * Instantiates a new Presentation.
     *
     * @param id           the id
     * @param name         the name
     * @param timeAsMinute the time as minute
     * @param type         the type
     */
    public Presentation(Long id, String name, Integer timeAsMinute, PresentationType type) {
        this.id = id;
        this.name = name;
        this.timeAsMinute = timeAsMinute;
        this.type = type;
    }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets time as minute.
     *
     * @return the time as minute
     */
    public Integer getTimeAsMinute() {
        return timeAsMinute;
    }

    /**
     * Sets time as minute.
     *
     * @param timeAsMinute the time as minute
     */
    public void setTimeAsMinute(Integer timeAsMinute) {
        this.timeAsMinute = timeAsMinute;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public PresentationType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
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
