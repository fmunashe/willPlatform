package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SpecialBequestDto extends BaseDto {
    private String personDetails;
    private String specialBequestInformation;
    private Client userId;
}
