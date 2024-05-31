package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersChildRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Surname is required")
    private String surname;
    @NotNull(message = "Date of birth is required")
    private LocalDate dob;
    @NotNull(message = "Trust age is required")
    private Integer trustAge;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
