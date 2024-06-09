package zw.co.zim.willplatform.utils.enums;

import lombok.Getter;

@Getter
public enum ProductNames {
    DIGITAL_WILL("DIGITAL_WILL"), DIGITAL_STORAGE("DIGITAL_STORAGE"), DIGITAL_EXECUTOR("DIGITAL_EXECUTOR"), DIGITAL_CHECK("DIGITAL_CHECK");

    private final String name;

    ProductNames(String name) {
        this.name = name;
    }

}
