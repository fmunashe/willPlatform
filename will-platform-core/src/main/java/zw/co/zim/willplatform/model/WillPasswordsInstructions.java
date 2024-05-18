package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WillPasswordsInstructions extends BaseEntity {
    private String instructions;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client UserId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
