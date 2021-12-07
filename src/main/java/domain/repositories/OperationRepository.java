package domain.repositories;


import domain.Operation;

public class OperationRepository extends Repository<Operation> {
    private static final OperationRepository INSTANCE = new OperationRepository();

    private OperationRepository() {}

    public static OperationRepository getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<Operation> getClassName() {
        return Operation.class;
    }

}

