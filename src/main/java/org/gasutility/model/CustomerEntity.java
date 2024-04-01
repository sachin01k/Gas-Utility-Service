package org.gasutility.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "CUSTOMERS")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(length = 100)
    private String name;
    private String phone;
    private String email;
    private String aadhar;
    private String address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private NewGasConnection newGasConnection;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ServiceRequestEntity> serviceRequest;
}
