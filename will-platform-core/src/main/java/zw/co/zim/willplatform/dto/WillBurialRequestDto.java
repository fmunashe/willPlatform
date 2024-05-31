package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.BurialType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WillBurialRequestDto extends BaseDto {
    private BurialType burialType;
    private String burialInformation;
    private Boolean livingWill;
    private Client userId;
}
