package org.gasutility.repository;

import org.gasutility.entities.CustomerEntity;
import org.gasutility.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

}
