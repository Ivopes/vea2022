package cz.vsb.swi.vea2022.services;

import cz.vsb.swi.vea2022.models.Product;
import cz.vsb.swi.vea2022.repositories.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements EntityService<Product>{
    @Autowired
    private EntityRepository<Product> productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void insert(Product entity) {
        productRepository.insert(entity);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }
}
