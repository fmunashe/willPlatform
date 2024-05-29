package zw.co.zim.willplatform.utils.enums;

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
