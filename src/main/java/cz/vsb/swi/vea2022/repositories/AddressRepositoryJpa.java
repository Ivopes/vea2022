package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.models.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AddressRepositoryJpa implements EntityRepository<Address> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Address> getAll() {
        return em.createQuery("select a from Address a", Address.class).getResultList();
    }

    @Override
    public Address findById(long id) {
        return em.find(Address.class, id);
    }

    @Override
    @Transactional
    public void insert(Address entity) {
        if (entity.getId() == 0) {
            var persons = entity.getPersons();
            persons.forEach(o -> em.persist(o));
            //em.persist(product.getOrders());
            em.persist(entity);
        } else {
            for (var person: entity.getPersons()) {
                em.merge(person);
            }

            em.merge(entity);
        }
    }

    @Override
    public void delete(long id) {

    }
}
