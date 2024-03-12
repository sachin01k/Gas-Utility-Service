package org.gasutility.repository;

import org.gasutility.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	
	@Query(value="Select * from admins where username = ? AND password = ?", nativeQuery=true)
	public Admin check(String username, String password);
}
