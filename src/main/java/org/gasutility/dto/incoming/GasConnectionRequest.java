package org.gasutility.dto.incoming;

import lombok.Data;

@Data
public class GasConnectionRequest {

    private Integer customerId;
    private String address;
}
