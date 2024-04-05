package org.gas_utility.dto.incoming;

import lombok.Data;

@Data
public class ServiceRequest {

    private Integer customerId;
    private String requestType;
    private String description;
}
