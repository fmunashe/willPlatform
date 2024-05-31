package zw.co.zim.willplatform.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDto {
    private Long id;
    private RecordStatus recordStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
