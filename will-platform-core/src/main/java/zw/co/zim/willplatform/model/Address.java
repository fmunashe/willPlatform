package zw.co.zim.willplatform.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String country;
}
