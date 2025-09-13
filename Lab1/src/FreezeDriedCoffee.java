class Freeze extends InstantCoffee {
    private boolean isPacked;

    public Freeze(String countryOfOrigin, double basePrice, int jarVolume, boolean isPacked) {
        super("Сублимированный", basePrice, countryOfOrigin, jarVolume);
        this.isPacked = isPacked;
    }

    // Переопределяем расчет цены: цена за кг * вес + надбавка за мытый процесс
    @Override
    public double calculatePrice() {
        double total = super.calculatePrice(); // Базовая стоимость
        if (isPacked) {
            total += 0.75; // Надбавка за мытый процесс
        }
        return total;
    }

    public boolean isPacked() {
        return isPacked;
    }

    public void setPacked(boolean packed) {
        isPacked = packed;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Тип: %s, Итоговая цена: %.2f руб.", (isPacked ? "Фасованный" : "Не фасованный"), calculatePrice());
    }
}