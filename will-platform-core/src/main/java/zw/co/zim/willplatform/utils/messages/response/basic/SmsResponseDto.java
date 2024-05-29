package zw.co.zim.willplatform.utils.messages.response.basic;

import lombok.Builder;
import lombok.Data;
import zw.co.zim.willplatform.utils.enums.DeliveryState;


@Data
@Builder
public class SmsResponseDto {
    private String reference;
    private DeliveryState state;
    private String summary;
}
