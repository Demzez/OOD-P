import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.PharmacyModel;
import view.LoginView;
import view.RegisterView;
import view.PharmacyView;
import controller.AuthController;
import controller.PharmacyController;

public class Main extends Application {
    private PharmacyModel model;
    private LoginView loginView;
    private RegisterView registerView;
    private PharmacyView pharmacyView;
    private AuthController authController;
    private PharmacyController pharmacyController;
    private Stage primaryStage;
    private Scene mainScene; // Одна основная сцена

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Инициализация компонентов
        model = new PharmacyModel();
        loginView = new LoginView();
        registerView = new RegisterView();
        pharmacyView = new PharmacyView();

        // Создаем ОДНУ сцену с пустым корнем
        mainScene = new Scene(new StackPane(), 800, 600);
        primaryStage.setScene(mainScene);

        // Инициализация контроллеров
        authController = new AuthController(model, loginView, registerView);
        pharmacyController = new PharmacyController(model, pharmacyView, loginView);

        // Настройка callback'ов
        authController.setOnLoginSuccess(this::showPharmacyView);
        authController.setShowRegisterView(this::showRegisterView);
        authController.setShowLoginView(this::showLoginView);
        pharmacyController.setShowLoginView(this::showLoginView);

        // Показ начального экрана
        showLoginView();

        primaryStage.setTitle("Аптека - Система управления");
        primaryStage.show();
    }

    private void showLoginView() {
        mainScene.setRoot(loginView); // Меняем корневой узел
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
    }

    private void showRegisterView() {
        mainScene.setRoot(registerView); // Меняем корневой узел
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
    }

    private void showPharmacyView() {
        pharmacyController.refreshMedicines();
        mainScene.setRoot(pharmacyView); // Меняем корневой узел
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}