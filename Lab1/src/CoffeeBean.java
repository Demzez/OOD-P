abstract class CoffeeBean extends Coffee {
    private String countryOfOrigin;
    private int weight;

    public CoffeeBean(String name, double basePrice, String countryOfOrigin, double weight) {
        super(name, basePrice);
        this.countryOfOrigin = countryOfOrigin;
        setWeight(weight);
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        else {
            this.weight = 0;
            }
        }
    }

    @Override
    public double calculatePrice() {

        return getBasePrice() * weight;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Страна: %s, Вес: %.d г", countryOfOrigin, weight);
    }
}