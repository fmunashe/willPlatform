package zw.co.zim.willplatform.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {
    private String nameOfInstitution;
    private Double cardValue;
    private Long clientId;
}
