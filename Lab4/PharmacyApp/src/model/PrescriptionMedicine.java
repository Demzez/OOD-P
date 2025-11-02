package model;

public class PrescriptionMedicine extends Medicine {
    private String prescriptionRequired;

    public PrescriptionMedicine(String name, double price, int quantity,
                                String manufacturer, String prescriptionRequired) {
        super(name, price, quantity, manufacturer, "Prescription");
        this.prescriptionRequired = prescriptionRequired;
    }

    @Override
    public String getType() {
        return "Prescription Medicine";
    }

    public String getPrescriptionRequired() {
        return prescriptionRequired;
    }
}
