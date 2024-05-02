package zw.co.zim.willplatform.enums;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");

    String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
