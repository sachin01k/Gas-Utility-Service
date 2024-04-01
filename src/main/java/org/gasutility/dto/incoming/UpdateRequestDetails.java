package org.gasutility.dto.incoming;

import lombok.Data;

@Data
public class UpdateRequestDetails {

    private Integer requestId;
    private String requestType;
    private String description;
}
