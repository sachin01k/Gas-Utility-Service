package org.gasutility.model;

import jakarta.persistence.*;
import lombok.Data;
import org.gasutility.enums.RequestStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name="GAS_CONNECTIONS")
public class NewGasConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connectionId;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate requestDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updateDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    @OneToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

}
