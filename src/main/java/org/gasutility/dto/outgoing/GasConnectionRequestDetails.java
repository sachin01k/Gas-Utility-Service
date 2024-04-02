package org.gasutility.dto.outgoing;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.gasutility.enums.RequestStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
