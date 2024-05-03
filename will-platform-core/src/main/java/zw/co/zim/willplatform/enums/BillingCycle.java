package zw.co.zim.willplatform.enums;

public enum BillingCycle {
    MONTHLY("MONTHLY"), WEEKLY("WEEKLY"), FORTNITE("FORTNITE"), ANNUALLY("ANNUALLY"), BIANNUAL("BIANNUAL");

    private final String cycle;

    BillingCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycle() {
        return cycle;
    }
}
