import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }
    
    public static void initializeStorage(StorageOfCoffee storage) {
        // Арабика - 10 позиций
        storage.addProduct(new Arabica("Бразилия", 15.0, 500, true));
        storage.addProduct(new Arabica("Колумбия", 18.0, 250, false));
        storage.addProduct(new Arabica("Эфиопия", 20.0, 1000, true));
        storage.addProduct(new Arabica("Кения", 22.0, 300, false));
        storage.addProduct(new Arabica("Гватемала", 16.0, 750, true));
        storage.addProduct(new Arabica("Коста-Рика", 19.0, 400, false));
        storage.addProduct(new Arabica("Перу", 14.0, 600, true));
        storage.addProduct(new Arabica("Мексика", 13.0, 350, false));
        storage.addProduct(new Arabica("Танзания", 21.0, 200, true));
        storage.addProduct(new Arabica("Ямайка", 25.0, 150, false));

        // Робуста - 10 позиций
        storage.addProduct(new Robusta("Вьетнам", 12.0, 500, true));
        storage.addProduct(new Robusta("Индия", 10.0, 250, false));
        storage.addProduct(new Robusta("Индонезия", 11.0, 800, true));
        storage.addProduct(new Robusta("Уганда", 9.0, 450, false));
        storage.addProduct(new Robusta("Кот-д'Ивуар", 8.0, 700, true));
        storage.addProduct(new Robusta("Камерун", 10.5, 300, false));
        storage.addProduct(new Robusta("Мадагаскар", 13.0, 550, true));
        storage.addProduct(new Robusta("Филиппины", 11.5, 400, false));
        storage.addProduct(new Robusta("Таиланд", 14.0, 350, true));
        storage.addProduct(new Robusta("Бразилия", 12.5, 600, false));

        // Сублимированный кофе - 8 позиций
        storage.addProduct(new Freeze("Швейцария", 8.0, 200, true));
        storage.addProduct(new Freeze("Германия", 7.5, 250, false));
        storage.addProduct(new Freeze("Италия", 9.0, 150, true));
        storage.addProduct(new Freeze("Франция", 8.5, 300, false));
        storage.addProduct(new Freeze("США", 7.0, 400, true));
        storage.addProduct(new Freeze("Япония", 10.0, 100, false));
        storage.addProduct(new Freeze("Колумбия", 8.2, 350, true));
        storage.addProduct(new Freeze("Бразилия", 7.8, 500, false));

        // Порошковый кофе - 8 позиций
        storage.addProduct(new Spray("США", 6.0, 150, false));
        storage.addProduct(new Spray("Китай", 5.5, 200, true));
        storage.addProduct(new Spray("Россия", 6.5, 250, false));
        storage.addProduct(new Spray("Польша", 5.8, 300, true));
        storage.addProduct(new Spray("Турция", 7.0, 100, false));
        storage.addProduct(new Spray("Испания", 6.2, 350, true));
        storage.addProduct(new Spray("Великобритания", 6.8, 400, false));
        storage.addProduct(new Spray("Нидерланды", 5.9, 450, true));

        // Дополнительные премиум позиции - 4 позиции
        storage.addProduct(new Arabica("Гавайи", 35.0, 100, true));
        storage.addProduct(new Arabica("Ямайка Блю Маунтин", 45.0, 250, false));
        storage.addProduct(new Freeze("Швейцария Премиум", 15.0, 100, true));
        storage.addProduct(new Spray("Органический", 12.0, 200, false));
    }
}

