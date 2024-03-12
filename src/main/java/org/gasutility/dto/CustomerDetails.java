package org.gasutility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
	
	private String name;
	private String phone;	
	private String email;
	private Long aadharId;	
	private Address address;
}
