package org.gasutility.repository;

import org.gasutility.entities.ServiceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequestEntity,Integer> {
}
