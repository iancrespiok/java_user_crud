package domain;

import persistence.IdentifiableEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_")
public class Group extends IdentifiableEntity {
    private String name;
    private String description;
    @ManyToMany @JoinTable(name = "permitions") @JoinColumn(referencedColumnName = "operation")
    private Set<Operation> availableOperations;
    @ManyToMany @JoinTable(name = "groupAssignment") @JoinColumn(referencedColumnName = "user")
    private List<User> users;

    public Group(String nombre,String description) {
        this.name = nombre;
        this.description = description;
        this.availableOperations = new HashSet<>();
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public Group() {

    }

    public void addUser (User user) {
        users.add(user);
    }

    public void addOperation (Operation operation) {
        availableOperations.add(operation);
    }
}
