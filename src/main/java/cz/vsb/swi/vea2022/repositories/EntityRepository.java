package cz.vsb.swi.vea2022.repositories;

import java.util.List;

public interface EntityRepository<T> {
    List<T> getAll();
    T findById(long id);
    void insert(T entity);
    void delete(long id);
}
