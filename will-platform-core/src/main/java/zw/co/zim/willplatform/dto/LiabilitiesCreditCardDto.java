package zw.co.zim.willplatform.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiabilitiesCreditCardDto {
    private Long id;
    private String nameOfInstitution;
    private Double cardValue;
    private Client userId;
    private RecordStatus recordStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
