package org.gas_utility.repository;

import org.gas_utility.model.NewGasConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * GasConnectionRepo is a Interface which extends JpaRepository.
 * It provides database connectivity to NewGasConnection Entity.
 * @author Sachin Kamble
 * @since 17.0
 */
@Repository
public interface GasConnectionRepo extends JpaRepository<NewGasConnection,Integer> {

}
