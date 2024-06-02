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
public class GuardianRequest {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Name is required")
    private String name;
    private String middleName;
    @NotNull(message = "Surname is required")
    private String surname;
    @NotNull(message = "DOB is required")
    private LocalDate dob;
    @NotNull(message = "National ID Number is required")
    private String IdNumber;
    private String passportNumber;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @NotNull(message = "Relationship is required")
    private Relationship relationship;
    @NotNull(message = "Contact number is required")
    private String contactNumber;
    @NotNull(message = "Email address is required")
    private String email;
    @NotNull(message = "Client id is required")
    private Long clientId;
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String country;
}
