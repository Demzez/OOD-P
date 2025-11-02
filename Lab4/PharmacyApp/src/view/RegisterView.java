package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RegisterView extends VBox {
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private Button registerButton;
    private Button backButton;
    private Label messageLabel;

    public RegisterView() {
        initializeView();
    }

    private void initializeView() {
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Text title = new Text("Регистрация");
        title.setFont(Font.font(20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        emailField = new TextField();
        emailField.setPromptText("Email");

        grid.add(new Label("Имя пользователя:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);

        registerButton = new Button("Зарегистрироваться");
        backButton = new Button("Назад");

        HBox buttonBox = new HBox(10, registerButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        getChildren().addAll(title, grid, buttonBox, messageLabel);
    }

    // Геттеры
    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }
}
