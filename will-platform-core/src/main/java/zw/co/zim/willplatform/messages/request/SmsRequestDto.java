package zw.co.zim.willplatform.messages.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SmsRequestDto {
    private String from;
    private List<String> to;
    private String message;
    private String notifyUrl;
}
