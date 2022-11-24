package cz.vsb.swi.vea2022.services;

import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.repositories.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements EntityService<Order>{
    @Autowired
    EntityRepository<Order> orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public void insert(Order entity) {

        orderRepository.insert(entity);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }
}
