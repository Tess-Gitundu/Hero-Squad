package models;

import java.util.ArrayList;
import java.util.Objects;

public class Hero {
    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private int id;
    private int squadId;

    public Hero(String name, int age, String specialPower, String weakness, int id, int squadId) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.id = id;
        this.squadId = squadId;
    }

    public static void clearAllHeroes() {
    }

    public static Hero findById(int idOfHeroToDelete) {
        return null;
    }

    public static ArrayList<Hero> getAll() {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getName() == hero.getName() &&
                getId() == hero.getId() &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getSquadId(), hero.getSquadId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSpecialPower(), getSquadId());
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(String specialPower) {
        this.specialPower = specialPower;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void deleteHero() {
    }

    public void update(String newContent) {
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}
