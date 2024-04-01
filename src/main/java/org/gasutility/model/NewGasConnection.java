package org.gasutility.model;

import jakarta.persistence.*;
import lombok.Data;
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

    private String address;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate requestDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updateDate;

    private String status = "pending";

    @OneToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

}
