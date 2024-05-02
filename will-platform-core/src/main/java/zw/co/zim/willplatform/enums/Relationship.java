package zw.co.zim.willplatform.enums;

public enum Relationship {
    PARENT("PARENT"), COUSIN("COUSIN"), SIBLING("SIBLING"), BIOLOGICAL_CHILD("BIOLOGICAL_CHILD");

    private final String relation;

    Relationship(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }
}
