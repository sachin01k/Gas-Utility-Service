package org.gas_utility.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.gas_utility.enums.RequestStatus;
import org.gas_utility.enums.RequestType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

/**
 * ServiceRequestEntity class is mapped to REQUESTS Relation in Database.
 * @author Sachin kamble
 * @since 17.0
 */
@Data
@Entity
@Table(name="REQUESTS")
public class ServiceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Size(min = 10, max = 255, message="Description must be between 10-255 letters.")
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

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
