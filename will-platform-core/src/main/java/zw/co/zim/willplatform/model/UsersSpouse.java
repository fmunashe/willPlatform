package zw.co.zim.willplatform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.zim.willplatform.enums.CiviallyMarriedStatus;
import zw.co.zim.willplatform.enums.MaritalStatus;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersSpouse extends BaseEntity {
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Enumerated(EnumType.STRING)
    private CiviallyMarriedStatus civillyMarriedStatus;
    @ManyToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
