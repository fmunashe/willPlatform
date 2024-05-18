package zw.co.zim.willplatform.model;


import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.enums.Gender;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.enums.Relationship;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersBeneficiary extends BaseEntity {
    private String title;
    private String name;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String idNumber;
    private String passportNumber;
    private String trustNumber;
    private String trustName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    private Double percentage;
    private String contactNumber;
    private String email;
    private String instructions;
    private Boolean overEighteen;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
