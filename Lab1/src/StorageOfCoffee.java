import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Comparator;

class StorageOfCoffee {
    private List<Coffee> products;

    public StorageOfCoffee() { products = new ArrayList<>(); }

    public void addProduct(Coffee coffee) {
        products.add(coffee);
    }

    public void showStorage() {
        if (products.isEmpty()) {
            System.out.println("Полки пусты.");
            return;
        }
        System.out.println("\n=== Ассортимент Кофе ===");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + "." + products.get(i));
        }
    }

    public void BuySomeProduct(int number) {
        if (number-1 <= products.size() && number >= 0) {
            products.remove(products.get(number-1));
        }
        else {
            System.out.println("Покупка отклонена, на складе нет товара под данным номером.");
        }
    }

    public void clearStorage() {
        products.clear();
        System.out.println("Хранилище очищено.");
    }

    public List<Coffee> getProducts() {
        return products;
    }



    //Stream API
    public void sortProductsByPrice() {
        products = products.stream()
                .sorted(Comparator.comparingDouble(Coffee::getBasePrice))
                .collect(Collectors.toList());
        System.out.println("Товары отсортированы по цене (по возрастанию)");
    }

    public List<Coffee> filterProductsByMaxPrice(double maxPrice) {
        List<Coffee> filtered = products.stream()
                .filter(coffee -> coffee.getBasePrice() <= maxPrice)
                .collect(Collectors.toList());
        System.out.printf("Отфильтровано товаров с ценой до %.2f руб: %d шт.\n", maxPrice, filtered.size());
        return filtered;
    }

    public Map<String, Double> getProductPriceMap() {
        Map<String, Double> priceMap = products.stream()
                .collect(Collectors.toMap(
                        Coffee::getName,
                        Coffee::getBasePrice,
                        (existing, replacement) -> existing
                ));
        System.out.println("Создана карта цен товаров");
        return priceMap;
    }


    public Map<String, List<Coffee>> groupProductsByCountry() {
        Map<String, List<Coffee>> grouped = products.stream()
                .collect(Collectors.groupingBy(coffee -> {
                    if (coffee instanceof CoffeeBean) {
                        return ((CoffeeBean) coffee).getCountryOfOrigin();
                    } else if (coffee instanceof InstantCoffee) {
                        return ((InstantCoffee) coffee).getCountryOfOrigin();
                    }
                    return "Неизвестно";
                }));
        System.out.println("Товары сгруппированы по странам: " + grouped.keySet());
        return grouped;
    }

    public Set<String> getUniqueTypesOfCoffee() {
        Set<String> countries = products.stream()
                .map(Coffee::getName)
                .collect(Collectors.toSet());
        System.out.println("Все виды кофе: " + countries);
        return countries;
    }
}