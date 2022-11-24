package cz.vsb.swi.vea2022.services;

import cz.vsb.swi.vea2022.models.Person;

import java.util.List;

public interface EntityService<T> {
    List<T> getAll();

    void insert(T entity);

    T findById(long id);
    void delete(long id);

}
