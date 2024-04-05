package org.gas_utility.repository;

import org.gas_utility.model.ServiceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ServiceRequestRepo is a Interface which extends JpaRepository.
 * It provides database connectivity to ServiceRequestEntity Entity.
 * @author Sachin Kamble
 * @since 17.0
 */
@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequestEntity,Integer> {
}
