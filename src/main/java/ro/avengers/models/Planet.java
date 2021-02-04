package ro.avengers.models;

import java.io.Serializable;

public class Planet implements Serializable {

    private int id;
    private String name;
    private String description;
    private PlanetModifier modifiers;

    public Planet() {
    }

    public Planet(int id, String name, String description, PlanetModifier modifiers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modifiers = modifiers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlanetModifier getModifiers() {
        return modifiers;
    }

    public void setModifiers(PlanetModifier planetModifier) {
        this.modifiers = planetModifier;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modifiers=" + modifiers +
                '}';
    }
}
