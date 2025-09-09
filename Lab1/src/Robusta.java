// Конкретные классы кофейных зерен
class Robusta extends CoffeeBean {
    private boolean isWashed;

    public Robusta(String countryOfOrigin, double basePrice, int weight, boolean isWashed) {
        super("Робуста", basePrice, countryOfOrigin, weight);
        this.isWashed = isWashed;
    }

    // Переопределяем расчет цены: цена за кг * вес + надбавка за мытый процесс
    @Override
    public double calculatePrice() {
        double total = super.calculatePrice(); // Базовая стоимость
        if (isWashed) {
            total += 10 * getWeight(); // Надбавка за мытый процесс
        }
        return total;
    }

    public boolean isWashed() {
        return isWashed;
    }

    public void setWashed(boolean washed) {
        isWashed = washed;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Тип: %s, Итоговая цена: %.2f руб.", (isWashed ? "Мытый" : "Натуральный"), calculatePrice());
    }
}