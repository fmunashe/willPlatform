package zw.co.zim.willplatform.enums;

public enum CaseType {
    SALES("SALES"), SUPPORT("SUPPORT"), WILL_COLLECTION("WILL_COLLECTION"), ESTATE_PLANNER("ESTATE_PLANNER"), WILL_REVIEW("WILL_REVIEW");
    private final String type;


    CaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
