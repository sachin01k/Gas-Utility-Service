package org.gasutility.model;

import jakarta.persistence.*;
import lombok.Data;
import org.gasutility.enums.RequestStatus;
import org.gasutility.enums.RequestType;
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

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

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
