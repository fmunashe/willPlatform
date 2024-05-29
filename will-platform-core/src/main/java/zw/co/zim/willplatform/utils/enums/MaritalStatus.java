package zw.co.zim.willplatform.utils.enums;

public enum MaritalStatus {
    MARRIED("MARRIED"), DIVORCED("DIVORCED"), DECEASED("DECEASED"), WIDOW("WIDOW"), WIDOWER("WIDOWER");
    private final String status;

    MaritalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
