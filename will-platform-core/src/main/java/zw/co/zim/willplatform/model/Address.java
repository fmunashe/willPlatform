package zw.co.zim.willplatform.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String country;
}
