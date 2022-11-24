package cz.vsb.swi.vea2022.services;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.repositories.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressService implements EntityService<Address> {
    @Autowired
    private EntityRepository<Address> addressEntityRepository;
    @Override
    public List<Address> getAll() {
        return addressEntityRepository.getAll();
    }

    @Override
    public void insert(Address entity) {

    }

    @Override
    public Address findById(long id) {
        return null;
    }
}
