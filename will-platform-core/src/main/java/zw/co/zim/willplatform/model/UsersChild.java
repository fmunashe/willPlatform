package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersChild extends BaseEntity {
    private String name;
    private String surname;
    private LocalDate dob;
    private Integer trustAge;
    @ManyToOne
    private User userId;

}
