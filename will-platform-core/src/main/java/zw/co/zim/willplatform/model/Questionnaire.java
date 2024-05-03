package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Questionnaire extends BaseEntity {
    @OneToOne
    private Client userId;

}
