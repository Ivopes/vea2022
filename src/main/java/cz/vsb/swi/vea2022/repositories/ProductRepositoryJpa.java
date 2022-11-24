package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Chips;
import cz.vsb.swi.vea2022.models.ColoredPaper;
import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Repository
public class ProductRepositoryJpa implements EntityRepository<Product> {
    @PersistenceContext
    private EntityManager em;

    private static boolean loaded = false;

    @Override
    @Transactional
    public List<Product> getAll() {
        if (!loaded) {
            Product p = new Chips();
            p.randomValues();
            em.persist(p);
            p = new Chips();
            p.randomValues();
            em.persist(p);
            p = new ColoredPaper();
            p.randomValues();
            em.persist(p);
            p = new ColoredPaper();
            p.randomValues();
            em.persist(p);


            loaded = true;
        }

        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Override
    @Transactional
    public void insert(Product product) {
        if (product.getId() == 0) {
            var orders = product.getOrders();
            orders.forEach(o -> em.persist(o));
            //em.persist(product.getOrders());
            em.persist(product);
        } else {
            List<Order> orders = em.merge(product.getOrders());
            product.setOrders(orders);
            em.merge(product);
            product.setName(product.getName() + "aaaa");
        }
    }

    @Override
    public void delete(long id) {

    }
}
