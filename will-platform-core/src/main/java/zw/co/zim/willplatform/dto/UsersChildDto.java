package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsersChildDto extends BaseDto {
    private String name;
    private String surname;
    private LocalDate dob;
    private Integer trustAge;
    private Client userId;
}
