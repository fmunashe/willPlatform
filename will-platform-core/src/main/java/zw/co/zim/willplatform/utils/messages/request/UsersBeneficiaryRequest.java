package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.zim.willplatform.utils.enums.Gender;
import zw.co.zim.willplatform.utils.enums.Relationship;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersBeneficiaryRequest {
    private String title;
    @NotNull(message = "Name is required")
    private String name;
    private String middleName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Date of birth is required")
    private LocalDate dob;
    private String idNumber;
    private String passportNumber;
    private String trustNumber;
    private String trustName;
    private Gender gender;
    private Relationship relationship;
    @NotNull(message = "Percentage benefit is required")
    private Double percentage;
    @NotNull(message = "Contact number is required")
    private String contactNumber;
    @NotNull(message = "Email address is required")
    private String email;
    private String instructions;
    private Boolean overEighteen;
    @NotNull(message = "Client id is required")
    private Long clientId;
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String country;
}
