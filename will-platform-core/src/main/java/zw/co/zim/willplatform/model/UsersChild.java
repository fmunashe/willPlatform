package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersChild extends BaseEntity {
    private String name;
    private String surname;
    private LocalDate dob;
    private Integer trustAge;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

}
