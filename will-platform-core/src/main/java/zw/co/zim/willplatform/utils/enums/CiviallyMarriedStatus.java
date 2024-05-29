package zw.co.zim.willplatform.utils.enums;

public enum CiviallyMarriedStatus {
    IN_COMMUNITY_OF_PROPERTY("IN_COMMUNITY_OF_PROPERTY"), OUT_OF_COMMUNITY_OF_PROPERTY("OUT_OF_COMMUNITY_OF_PROPERTY");

    private final String status;

    CiviallyMarriedStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
