package com.gnome.crossing.backend.db.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gnome.crossing.backend.db.DbOperation;
import com.gnome.crossing.backend.db.dao.mapper.ItemLocationMapper;
import com.gnome.crossing.backend.db.dao.repository.ItemLocationRespository;
import com.gnome.crossing.backend.model.ItemLocation;
import lombok.extern.slf4j.Slf4j;


/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class ItemLocationDao implements DbOperation<ItemLocation> {

    private static String CUSTOMER_ID_DOES_NOT_EXIST = "customer with id: %s does not exist.";

    @Autowired
    private ItemLocationMapper mapper;

    @Autowired
    private ItemLocationRespository itemLocationRespository;

    @Override
    public List<ItemLocation> findAll() {
        log.info("fetching customers.");
        return itemLocationRespository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

//    @Override
//    public Customer findById(long customerId) {
//        log.info("fetching itemLocation with id: {}.", customerId);
//        CustomerEntity entity = customerRepository.findById(customerId)
//            .orElseThrow(() -> new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, customerId)));
//        return mapper.toDto(entity);
//    }
//
    @Override
    public ItemLocation save(ItemLocation itemLocation) {
        log.info("saving itemLocation with desc: {}.", itemLocation.getDescription());
        return mapper.toDto(itemLocationRespository.save(mapper.toEntity(itemLocation)));
    }
//
//    @Override
//    public Customer update(Customer customer) {
//        log.info("updating customer with id: {}.", customer.getId());
//        return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
//    }
//
    @Override
    public void deleteById(long customerId) {
        log.info("deleting customer with id: {}.", customerId);
        itemLocationRespository.deleteById(customerId);
    }
//
//    @Override
//    public void deleteAll() {
//        log.info("deleting all customers records.");
//        customerRepository.deleteAll();
//    }
//
//    @Override
//    public boolean isEmailRegistered(String email) {
//        return customerRepository.existsByEmail(email);
//    }

}