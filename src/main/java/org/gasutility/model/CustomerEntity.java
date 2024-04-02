package org.gasutility.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "CUSTOMERS")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(length = 100, nullable = false)
    private String name;

    @Size(min = 10,max = 10,message = "Enter 10 Digit Phone Number.")
    @Column(length = 11, nullable = false,unique = true)
    private String phone;

    @Email(message="Enter valid email id.")
    @Column(length = 150, nullable = false,unique = true)
    private String email;

    @Size(min = 12,max = 12, message = "Enter 12 Digit Phone Number.")
    @Column(length = 13, nullable = false,unique = true)
    private String aadhar;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate registrationDate;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private NewGasConnection newGasConnection;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ServiceRequestEntity> serviceRequest;
}
