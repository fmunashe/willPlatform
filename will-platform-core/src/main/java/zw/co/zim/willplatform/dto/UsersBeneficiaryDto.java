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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsersBeneficiaryDto extends BaseDto {
    private String title;
    private String name;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String idNumber;
    private String passportNumber;
    private String trustNumber;
    private String trustName;
    private Gender gender;
    private Relationship relationship;
    private Double percentage;
    private String contactNumber;
    private String email;
    private String instructions;
    private Boolean overEighteen;
    private Client userId;
    private Address address;
}
