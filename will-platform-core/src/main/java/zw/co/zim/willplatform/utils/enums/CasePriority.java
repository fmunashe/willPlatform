package zw.co.zim.willplatform.utils.enums;

public enum CasePriority {
    P1("P1"),P2("P2"),P3("P3");

    private final String priority;

    CasePriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
