package models;

import java.util.Objects;

public class Squad {
    private int size;
    private String name;
    private String cause;

    public Squad(int size, String name, String cause) {
        this.size = size;
        this.name = name;
        this.cause = cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Squad)) return false;
        Squad squad = (Squad) o;
        return getName() == squad.getName() &&
                getSize() == squad.getSize() &&
                Objects.equals(getName(), squad.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCause());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getId() {
        return 0;
    }
}
