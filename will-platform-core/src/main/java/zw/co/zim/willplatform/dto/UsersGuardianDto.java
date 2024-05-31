package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.Gender;
import zw.co.zim.willplatform.utils.enums.Relationship;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UsersGuardianDto extends BaseDto {
    private String title;
    private String name;
    private String middleName;
    private String surname;
    private LocalDate dob;
    private String IdNumber;
    private String passportNumber;
    private Gender gender;
    private Relationship relationship;
    private String contactNumber;
    private String email;
    private Client userId;
    private Address address;
}
