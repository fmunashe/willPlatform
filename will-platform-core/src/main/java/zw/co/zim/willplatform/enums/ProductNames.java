package zw.co.zim.willplatform.enums;

public enum ProductNames {
    DIGITAL_WILL("DIGITAL_WILL"), SMART_STORAGE("SMART_STORAGE"), SMART_EXECUTOR("SMART_EXECUTOR"), SMART_CHECK("SMART_CHECK");

    private final String name;

    ProductNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
