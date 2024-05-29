package zw.co.zim.willplatform.utils.enums;

public enum CaseType {
    SALES("SALES"), SUPPORT("SUPPORT"), WILL_COLLECTION("WILL_COLLECTION"), ESTATE_PLANNER("ESTATE_PLANNER"), WILL_REVIEW("WILL_REVIEW"), UP_SELLING("UP_SELLING");
    private final String type;


    CaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
