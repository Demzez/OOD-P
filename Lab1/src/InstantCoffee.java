abstract class InstantCoffee extends Coffee {
    private String countryOfOrigin;
    private int jarVolume;

    public InstantCoffee(String name, double basePrice, String countryOfOrigin, int jarVolume) {
        super(name, basePrice);
        this.countryOfOrigin = countryOfOrigin;
        setJarVolume(jarVolume);
    }

    public String getCountryOfOrigin() { return countryOfOrigin; }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getJarVolume() { return jarVolume; }

    public void setJarVolume(int jarVolume) {
        if (jarVolume > 0) {
            this.jarVolume = jarVolume;
        }
        else {
                this.jarVolume = 0;
        }
    }

    @Override
    public double calculatePrice() {

        return getBasePrice() * jarVolume / 1000;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Страна: %s, Объем: %d г", countryOfOrigin, jarVolume);
    }
}
