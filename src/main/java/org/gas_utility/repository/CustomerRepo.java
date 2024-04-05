package org.gas_utility.repository;

import org.gas_utility.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepo is a Interface which extends JpaRepository.
 * It provides database connectivity to CustomerEntity Entity.
 * @author Sachin Kamble
 * @since 17.0
 */
@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

    @Query(value="Select phone from customers where phone=?", nativeQuery = true)
    String findByPhone(String phone);

    @Query(value="Select email from customers where email=?", nativeQuery = true)
    String findByEmail(String email);

    @Query(value="Select aadhar from customers where aadhar=?", nativeQuery = true)
    String findByAadhar(String aadhar);
}
