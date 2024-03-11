package org.gasutility.models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Customer {
	
	@Id
	private Integer customerId;
	
	private String name;
	private String phone;
	private String email;
	private Long aadharId;
	private String address;
	
}
