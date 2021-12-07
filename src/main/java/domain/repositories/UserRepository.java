package domain.repositories;

import domain.Group;
import domain.OperationType;
import domain.User;
import persistence.PerThreadEntityManager;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository extends Repository<User> {
    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository() {}

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<User> getClassName() {
        return User.class;
    }

    public List<User> usersWithOperationTypeCRITERIA(Integer operationType) {
        CriteriaBuilder builder = PerThreadEntityManager.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Group> query = builder.createQuery(Group.class);
        Root<Group> root = query.from(Group.class);
        query.select(root).where(
            builder.equal(root.join("availableOperations").get("type"), operationType)
        );

        TypedQuery<Group> typedQuery = PerThreadEntityManager.getEntityManager().createQuery(query);
        List<Group> groupList = typedQuery.getResultList();

        List<User> users = groupList.stream().flatMap(g -> g.getUsers().stream()).collect(Collectors.toList());
        List<Long> ids = users.stream().map(u->u.getId()).collect(Collectors.toList());
        CriteriaBuilder builder1 = PerThreadEntityManager.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query1 = builder1.createQuery(User.class);
        Root<User> root1 = query1.from(User.class);
        query1.where(root1.get("id").in(ids));
        TypedQuery<User> typedQuery1 = PerThreadEntityManager.getEntityManager().createQuery(query1);

        return typedQuery1.getResultList().stream().distinct().toList();
    }

    public List<User> usersWithOperationTypeHQL(Integer operationType) {

        Query typedQuery1 = PerThreadEntityManager.getEntityManager().createQuery("SELECT u FROM Group g JOIN g.availableOperations o JOIN g.users u WHERE o.type = :type")
                .setParameter("type", OperationType.values()[operationType]);
        return typedQuery1.getResultList().stream().distinct().toList();
    }



}
