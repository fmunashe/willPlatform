package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.CiviallyMarriedStatus;
import zw.co.zim.willplatform.utils.enums.MaritalStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsersSpouseDto extends BaseDto {
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String mobile;
    private MaritalStatus maritalStatus;
    private CiviallyMarriedStatus civillyMarriedStatus;
    private Client userId;
}
