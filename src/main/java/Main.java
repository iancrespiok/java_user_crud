import domain.User;
import org.hibernate.SessionFactory;

import javax.persistence.*;

public class Main {
    public static void main(String args[]){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();


    }
}
