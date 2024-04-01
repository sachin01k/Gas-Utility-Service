package org.gasutility.dto.incoming;

import lombok.Data;

@Data
public class ServiceRequest {

    private Integer customerId;
    private String requestType;
    private String description;
}
