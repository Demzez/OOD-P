package model;

public class OTCMedicine extends Medicine {
    private String ageRestriction;

    public OTCMedicine(String name, double price, int quantity,
                       String manufacturer, String ageRestriction) {
        super(name, price, quantity, manufacturer, "OTC");
        this.ageRestriction = ageRestriction;
    }

    @Override
    public String getType() {
        return "Over-the-Counter Medicine";
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }
}
