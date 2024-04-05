package org.gas_utility.dto.incoming;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String phone;
    private String email;
    private String aadhar;
    private String address;
}
