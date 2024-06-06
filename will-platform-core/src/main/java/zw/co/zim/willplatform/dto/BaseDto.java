package zw.co.zim.willplatform.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public abstract class BaseDto implements Serializable {
    private Long id;
    private RecordStatus recordStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
