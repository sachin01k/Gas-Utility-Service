package org.gasutility.repository;



import java.util.List;

import org.gasutility.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface RequestRepository extends JpaRepository<ServiceRequest, Integer> {
	
	@Query(value="select * from requests where status='pending'", nativeQuery=true)
	public List<ServiceRequest> getCustomerRequests();
	
	@Modifying
	@Transactional
	@Query(value="update requests set status = 'accepted' where request_id = ? ", nativeQuery=true)
	public int updateRequestStatus(Integer requestId);
}
