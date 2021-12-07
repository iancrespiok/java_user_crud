package domain;

import persistence.IdentifiableEntity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User extends IdentifiableEntity {
    private String nombre;
    private String apellido;
    private String username;
    private LocalDateTime lastLogin;
    @ManyToOne
    private Group defaultGroup;


    public User(String nombre, String apellido, String username, Group defaultGroup) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.lastLogin = LocalDateTime.now();
        this.defaultGroup = defaultGroup;
    }

    public void setDefaultGroup(Group defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public User() {

    }


}
