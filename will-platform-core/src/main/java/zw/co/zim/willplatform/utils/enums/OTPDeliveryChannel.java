package zw.co.zim.willplatform.utils.enums;

import lombok.Getter;

@Getter
public enum OTPDeliveryChannel {
    SMS("SMS"),EMAIL("EMAIL");
    private final String name;

    OTPDeliveryChannel(String name) {
        this.name = name;
    }

}
