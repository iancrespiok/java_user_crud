import domain.Group;
import domain.Operation;
import domain.OperationType;
import domain.User;
import domain.repositories.OperationRepository;
import domain.repositories.UserRepository;
import org.junit.Test;
import domain.repositories.GroupRepository;
import persistence.PerThreadEntityManager;

import java.util.List;

public class Testing {
    Group g1;
    Group g2;
    Group adm;
    User ian;
    User tom;
    User jack;
    Operation readUF;
    Operation writeUF;
    Operation execUF;

    public void bootstrap(Boolean exception) {
        g1 = new Group("Grupo 1", "Descripcion grupo 1");
        g2 = new Group("Grupo 2", "Descripcion grupo 2");
        adm = new Group("Admins group", "Maximus user-group level");
        ian = new User("Ian", "Crespi", "iancrespiok", g1);
        tom = new User("Thomas","Adsaw", "tomadsaw", g1);
        jack = new User("Jack","Williams", "jack123", adm);
        readUF = new Operation("ReadUF", "Read user files", OperationType.B);
        writeUF = new Operation("WriteUF","Write user files", OperationType.B);
        execUF = new Operation("ExecUF","Exec user files", OperationType.B);
        try{
            PerThreadEntityManager.getEntityManager().getTransaction().begin();
            GroupRepository.getInstance().registrar(g1,g2,adm);
            UserRepository.getInstance().registrar(ian,tom,jack);
            OperationRepository.getInstance().registrar(readUF,writeUF,execUF);
            if(exception){throw new RuntimeException();}
            g1.addOperation(readUF);
            g1.addOperation(writeUF);
            g2.addOperation(execUF);
            g1.addUser(jack);
            g1.addUser(ian);
            g1.addUser(tom);
            g2.addUser(ian);
            PerThreadEntityManager.getEntityManager().getTransaction().commit();
        }catch (Exception e) {
            PerThreadEntityManager.getEntityManager().getTransaction().rollback();
        }
    }

    @Test
    public void test() {
        bootstrap(true);


        List<User> groupList = UserRepository.getInstance().usersWithOperationTypeHQL(1);

        List<Long> ids = groupList.stream().map(g -> g.getId()).toList();
        System.out.println(ids);
    }
}
