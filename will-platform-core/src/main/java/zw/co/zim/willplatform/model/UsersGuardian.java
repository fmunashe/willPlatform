package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.Gender;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.enums.Relationship;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersGuardian extends BaseEntity {
    private String title;
    private String name;
    private String middleName;
    private String surname;
    private LocalDate dob;
    private String IdNumber;
    private String passportNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    private String contactNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

}
