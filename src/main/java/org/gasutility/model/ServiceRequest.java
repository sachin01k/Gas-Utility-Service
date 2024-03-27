package org.gasutility.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceRequest {

    private Integer customerId;
    private String requestType;
    private String description;
    private String status;
    private LocalDate requestDate;
    private LocalDate updateDate;
}
