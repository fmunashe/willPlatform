package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import lombok.*;
import zw.co.zim.willplatform.enums.RoleEnum;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SystemUser extends BaseEntity {
    private String name;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private RoleEnum role;
}
