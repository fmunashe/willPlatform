package zw.co.zim.willplatform.utils.enums;

public enum BurialType {
    BURIAL("BURIAL"), CREMATION("CREMATION"), ORGAN_DONOR("ORGAN DONOR"), ORGAN_DONOR_AND_BURIAL("ORGAN DONOR AND BURIAL"), ORGAN_DONOR_AND_CREMATION("ORGAN DONOR AND CREMATION");

    private final String type;

    BurialType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
