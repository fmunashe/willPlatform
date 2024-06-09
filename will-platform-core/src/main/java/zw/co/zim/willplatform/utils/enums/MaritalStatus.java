package zw.co.zim.willplatform.utils.enums;

public enum MaritalStatus {
    MARRIED("MARRIED"), DIVORCED("DIVORCED"), DECEASED("DECEASED"), WIDOW("WIDOW"), WIDOWER("WIDOWER"), SINGLE("SINGLE");
    private final String status;

    MaritalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
