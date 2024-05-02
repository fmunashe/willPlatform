package zw.co.zim.willplatform.enums;

public enum BurialType {
    BURIAL("BURIAL"), CREMATION("CREMATION"), ORGAN_DONOR("ORGAN_DONOR");

    private final String type;

    BurialType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
