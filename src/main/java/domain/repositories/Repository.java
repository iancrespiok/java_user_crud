package domain.repositories;

import persistence.PerThreadEntityManager;

import javax.persistence.EntityManager;


public abstract class Repository<T> {
    protected abstract Class<T> getClassName();

    public void registrar(T t){
        EntityManager entityManager = PerThreadEntityManager.getEntityManager();
        entityManager.persist(t);
    }

    @SafeVarargs
    public final void registrar(T... t){
        EntityManager entityManager = PerThreadEntityManager.getEntityManager();
        for (T objects : t) {
            PerThreadEntityManager.getEntityManager().persist(objects);
        }
    }

    public void borrar(T t){
        EntityManager entityManager = PerThreadEntityManager.getEntityManager();
        entityManager.remove(t);
    }


}
