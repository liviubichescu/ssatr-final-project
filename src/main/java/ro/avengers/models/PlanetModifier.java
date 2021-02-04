package ro.avengers.models;

import java.io.Serializable;

public class PlanetModifier implements Serializable {

    private int heroAttackModifier;
    private int heroHealthModifier;
    private int villainAttackModifier;
    private int villainHealthModifier;

    public PlanetModifier() {
    }

    public PlanetModifier(int heroAttackModifier, int heroHealthModifier, int villainAttackModifier, int villainHealthModifier) {
        this.heroAttackModifier = heroAttackModifier;
        this.heroHealthModifier = heroHealthModifier;
        this.villainAttackModifier = villainAttackModifier;
        this.villainHealthModifier = villainHealthModifier;
    }

    public int getHeroAttackModifier() {
        return heroAttackModifier;
    }

    public void setHeroAttackModifier(int heroAttackModifier) {
        this.heroAttackModifier = heroAttackModifier;
    }

    public int getHeroHealthModifier() {
        return heroHealthModifier;
    }

    public void setHeroHealthModifier(int heroHealthModifier) {
        this.heroHealthModifier = heroHealthModifier;
    }

    public int getVillainAttackModifier() {
        return villainAttackModifier;
    }

    public void setVillainAttackModifier(int villainAttackModifier) {
        this.villainAttackModifier = villainAttackModifier;
    }

    public int getVillainHealthModifier() {
        return villainHealthModifier;
    }

    public void setVillainHealthModifier(int villainHealthModifier) {
        this.villainHealthModifier = villainHealthModifier;
    }

    @Override
    public String toString() {
        return "heroAttackModifier=" + heroAttackModifier +
                ", heroHealthModifier=" + heroHealthModifier +
                ", villainAttackModifier=" + villainAttackModifier +
                ", villainHealthModifier=" + villainHealthModifier;
    }
}
