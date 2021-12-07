package domain;

import persistence.IdentifiableEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Operation extends IdentifiableEntity {
    private String name;
    private String description;
    @Enumerated()
    private OperationType type;

    public Operation(String name, String description, OperationType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Operation() {

    }
}
