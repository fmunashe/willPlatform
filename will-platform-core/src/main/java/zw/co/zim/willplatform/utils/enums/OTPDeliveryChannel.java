package zw.co.zim.willplatform.utils.enums;

public enum OTPDeliveryChannel {
    SMS("SMS"),EMAIL("EMAIL");
    private final String name;

    OTPDeliveryChannel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
