package zw.co.zim.willplatform.messages.response.basic;

import lombok.Builder;
import lombok.Data;
import zw.co.zim.willplatform.enums.DeliveryState;


@Data
@Builder
public class SmsResponseDto {
    private String reference;
    private DeliveryState state;
    private String summary;
}
