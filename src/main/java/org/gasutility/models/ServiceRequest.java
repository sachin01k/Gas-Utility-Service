package org.gasutility.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="REQUESTS")
public class ServiceRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer requestId;
	
	private String type;
	private String description;
	
	private String status = "pending";
	
	@CreationTimestamp
	@Column(name="request_date", updatable=false)
	private LocalDateTime date;
	
	@UpdateTimestamp
	@Column(name="request_update", insertable=false)
	private LocalDateTime lastUpdated;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

}
