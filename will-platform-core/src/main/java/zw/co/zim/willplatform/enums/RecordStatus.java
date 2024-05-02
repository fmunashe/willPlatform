package zw.co.zim.willplatform.enums;

public enum RecordStatus {
    OPEN("OPEN"), CLOSED("CLOSED"), PENDING("PENDING"), INPROGRESS("INPROGRESS"), SENT("SENT"), DELIVERED("DELIVERED"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), DELETED("DELETED");
    private String status;

    RecordStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
