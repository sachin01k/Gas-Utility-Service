package org.gas_utility.dto.incoming;

import lombok.Data;

@Data
public class UpdateRequestDetails {

    private Integer requestId;
    private String requestType;
    private String description;
}
