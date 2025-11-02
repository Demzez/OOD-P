package controller;

import model.PharmacyModel;
import view.LoginView;
import view.PharmacyView;
import patterns.SearchStrategy;
import patterns.NameSearchStrategy;
import patterns.CategorySearchStrategy;

public class PharmacyController {
    private PharmacyModel model;
    private PharmacyView view;
    private LoginView loginView;
    private Runnable showLoginView;


    public PharmacyController(PharmacyModel model, PharmacyView view, LoginView loginView) {
        this.model = model;
        this.view = view;
        this.loginView = loginView;

        model.addObserver(view);
        setupEventHandlers();
        initializeView();
    }

    private void setupEventHandlers() {
        // Поиск лекарств
        view.getSearchButton().setOnAction(e -> handleSearch());

        // Выход из системы
        view.getLogoutButton().setOnAction(e -> handleLogout());

        // Изменение стратегии поиска
        view.getSearchTypeComboBox().setOnAction(e -> updateSearchStrategy());
    }

    private void initializeView() {
        // Загрузка всех лекарств
        view.displayMedicines(model.getAllMedicines());

        // Обновление информации о пользователе
        if (model.isLoggedIn()) {
            view.getUserInfoLabel().setText("Пользователь: " + model.getCurrentUser().getUsername());
        }
    }

    private void handleSearch() {
        String query = view.getSearchField().getText().trim();

        if (query.isEmpty()) {
            view.displayMedicines(model.getAllMedicines());
        } else {
            view.displayMedicines(model.searchMedicines(query));
        }
    }

    private void updateSearchStrategy() {
        String searchType = view.getSearchTypeComboBox().getValue();
        SearchStrategy strategy;

        if ("По категории".equals(searchType)) {
            strategy = new CategorySearchStrategy();
        } else {
            strategy = new NameSearchStrategy();
        }

        model.setSearchStrategy(strategy);
    }

    private void handleLogout() {
        model.logout();
        showLoginView.run();
    }

    public void refreshMedicines() {
        view.displayMedicines(model.getAllMedicines());
    }

    public void setShowLoginView(Runnable showLoginView) {
        this.showLoginView = showLoginView;
    }
}
