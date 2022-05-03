package models;

import java.util.Objects;

public class Hero {
    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private int id;

    public Hero(String name, int age, String specialPower, String weakness, int id) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getName() == hero.getName() &&
                getId() == hero.getId() &&
                Objects.equals(getName(), hero.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSpecialPower());
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
}
