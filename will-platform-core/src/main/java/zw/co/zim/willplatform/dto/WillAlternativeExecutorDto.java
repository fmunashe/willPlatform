package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class WillAlternativeExecutorDto extends BaseDto {
    private String executorDetails;
    private String email;
    private String contactNumber;
    private Address address;
    private Client userId;
}
