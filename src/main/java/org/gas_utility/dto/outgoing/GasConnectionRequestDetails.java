package org.gas_utility.dto.outgoing;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GasConnectionRequestDetails {

    private Integer connectionId;
    private String address;
    private LocalDate requestDate;
    private LocalDate updateDate;
    private String status;
}
