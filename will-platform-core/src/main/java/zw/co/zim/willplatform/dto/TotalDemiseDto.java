package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TotalDemiseDto extends BaseDto {
    private String beneficiaryDetails;
    private Client userId;
}
