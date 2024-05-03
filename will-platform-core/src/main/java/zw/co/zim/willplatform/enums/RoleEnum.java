package zw.co.zim.willplatform.enums;

public enum RoleEnum {
    SYSADMIN("SYSADMIN"), CLIENT("CLIENT"), SUPPORT("SUPPORT"), BROKER("BROKER"),
    USER("USER"), AGENT("AGENT"), WILL_PLANNER("WILL_PLANNER"), WILL_REVIEWER("WILL_REVIEWER");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
