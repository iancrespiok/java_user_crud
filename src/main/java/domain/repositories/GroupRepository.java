package domain.repositories;

import domain.Group;

public class GroupRepository extends Repository<Group> {
    private static final GroupRepository INSTANCE = new GroupRepository();

    private GroupRepository() {}

    public static GroupRepository getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<Group> getClassName() {
        return Group.class;
    }

}
