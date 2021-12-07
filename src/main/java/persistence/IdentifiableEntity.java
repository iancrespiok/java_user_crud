package persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdentifiableEntity {
    @Id
    @GeneratedValue
    Long id;

    public Long getId() {
        return id;
    }
}
