package sp.fr.toutdouxliste.model;

/**
 * Created by Formation on 11/01/2018.
 */

public class Tache {

    private String name;
    private Integer check;
    private Long id;

    public Tache() {
    }

    public Tache(String name, Integer check) {
        this.name = name;
        this.check = check;
    }

    public Tache(String name, Integer check, Long id) {
        this.name = name;
        this.check = check;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tache setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCheck() {
        return check;
    }

    public Tache setCheck(Integer check) {
        this.check = check;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Tache setId(Long id) {
        this.id = id;
        return this;
    }
}
