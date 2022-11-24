package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Order;
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
        return null;
    }

    @Override
    @Transactional
    public void insert(Address entity) {
    }
}
