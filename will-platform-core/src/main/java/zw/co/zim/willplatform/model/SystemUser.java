package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.enums.RoleEnum;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_users")
public class SystemUser extends BaseEntity {
    private String name;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
