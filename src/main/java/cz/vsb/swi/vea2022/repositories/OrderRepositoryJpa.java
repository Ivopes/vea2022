package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.models.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderRepositoryJpa implements EntityRepository<Order> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> getAll() {

        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    @Override
    @Transactional
    public void insert(Order entity) {
        if (entity.getId() == 0) {
/*            var products = entity.getProducts();
            for (var pr:
                 products) {
                em.merge(pr);
                pr.addOrders(entity);
            }
            em.persist(products);*/
            em.persist(entity);
        } else {
            List<Product> products = em.merge(entity.getProducts());
            entity.setProducts(products);
            em.merge(entity);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        var o = findById(id);
        em.remove(o);
    }
}
