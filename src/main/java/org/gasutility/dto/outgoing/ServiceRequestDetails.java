package org.gasutility.dto.outgoing;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ServiceRequestDetails {

    private Integer requestId;
    private String requestType;
    private String description;
    private String status;
    private LocalDate requestDate;
    private LocalDate updateDate;
}
