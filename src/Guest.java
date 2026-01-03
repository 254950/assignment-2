import java.util.Objects;

public class Guest extends Person {
    private String email;

    public Guest(int id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getRole() { return "Guest"; }

    @Override
    public String toString() {
        return "Guest{id=" + getId() + ", name='" + getName() + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        return o instanceof Guest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}

abstract class Person {
    private final int id;
    private String name;

    protected Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + "{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}