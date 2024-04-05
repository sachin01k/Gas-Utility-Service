package org.gas_utility.dto.outgoing;

import lombok.Data;

@Data
public class CustomerDetails {

    private Integer customerId;
    private String name;
    private String phone;
    private String email;
    private String aadhar;
    private String address;
}
