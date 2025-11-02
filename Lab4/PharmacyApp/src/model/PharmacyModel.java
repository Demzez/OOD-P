package model;


import patterns.Subject;
import patterns.SearchStrategy;
import patterns.NameSearchStrategy;
import java.util.ArrayList;
import java.util.List;


public class PharmacyModel extends Subject {
    private List<User> users;
    private List<Medicine> medicines;
    private User currentUser;
    private SearchStrategy searchStrategy;

    public PharmacyModel() {
        this.users = new ArrayList<>();
        this.medicines = new ArrayList<>();
        this.searchStrategy = new NameSearchStrategy(); // стратегия по умолчанию
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Добавляем тестовых пользователей
        users.add(new User("admin", "admin123", "admin@pharmacy.com"));
        users.add(new User("user1", "password1", "user1@email.com"));

        // Добавляем лекарства
        medicines.add(new PrescriptionMedicine("Амоксициллин", 15.99, 50, "PharmaCorp", "Требуется рецепт"));
        medicines.add(new PrescriptionMedicine("Ибупрофен", 8.50, 100, "MedLab", "Требуется рецепт"));
        medicines.add(new OTCMedicine("Парацетамол", 5.99, 200, "HealthPlus", "18+"));
        medicines.add(new OTCMedicine("Витамин C", 12.99, 150, "NatureWell", "Без ограничений"));
        medicines.add(new PrescriptionMedicine("Левомицетин", 22.50, 30, "BioPharm", "Требуется рецепт"));
    }

    // Методы для работы с пользователями
    public boolean registerUser(String username, String password, String email) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            return false;
        }
        users.add(new User(username, password, email));
        notifyObservers("Пользователь " + username + " успешно зарегистрирован");
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                notifyObservers("Пользователь " + username + " вошел в систему");
                return true;
            }
        }
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            notifyObservers("Пользователь " + currentUser.getUsername() + " вышел из системы");
            currentUser = null;
        }
    }

    // Методы для работы с лекарствами
    public List<Medicine> getAllMedicines() {
        return new ArrayList<>(medicines);
    }

    public List<Medicine> searchMedicines(String query) {
        return searchStrategy.search(medicines, query);
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
        notifyObservers("Стратегия поиска изменена");
    }

    // Геттеры
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
