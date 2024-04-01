package org.gasutility.repository;

import org.gasutility.model.NewGasConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasConnectionRepo extends JpaRepository<NewGasConnection,Integer> {

}
