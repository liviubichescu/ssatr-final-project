package ro.avengers.models;

import java.io.Serializable;

public class MarvelCharacter implements Serializable {

    private int id;
    private String name;
    private String description;
    private int attack;
    private int health;
    private boolean isVillain;

    public MarvelCharacter() {
    }

    public MarvelCharacter(int id, String name, String description, int attack, int health, boolean isVillain) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attack = attack;
        this.health = health;
        this.isVillain = isVillain;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        if (this.attack < 0)
            this.attack = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0)
            this.health = 0;
    }

    public boolean isVillain() {
        return isVillain;
    }

    public void setIsVillain(boolean villain) {
        isVillain = villain;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attack=" + attack +
                ", health=" + health +
                '}';
    }

}
