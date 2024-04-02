package org.gasutility.repository;

import org.gasutility.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

    @Query(value="Select phone from customers where phone=?", nativeQuery = true)
    String findByPhone(String phone);

    @Query(value="Select email from customers where email=?", nativeQuery = true)
    String findByEmail(String email);

    @Query(value="Select aadhar from customers where aadhar=?", nativeQuery = true)
    String findByAadhar(String aadhar);
}
