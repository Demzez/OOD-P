abstract class Coffee {
    private String name;
    private double basePrice;

    public Coffee(String name, double basePrice) {
        this.name = name;
        setBasePrice(basePrice);
    }

    public abstract double calculatePrice();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice >= 0) {
            this.basePrice = basePrice;
        } else {
            this.basePrice = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Базовая цена: %.2f руб.)", name, basePrice);
    }
}