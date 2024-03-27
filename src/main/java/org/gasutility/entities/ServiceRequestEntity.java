package org.gasutility.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name="REQUESTS")
public class ServiceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    private String requestType;

    private String description;

    private String status = "pending";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate requestDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

}
