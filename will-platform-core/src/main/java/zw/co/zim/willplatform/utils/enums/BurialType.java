package zw.co.zim.willplatform.utils.enums;

public enum BurialType {
    BURIAL("BURIAL"), CREMATION("CREMATION"), DONATION("DONATION");

    private final String type;

    BurialType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
