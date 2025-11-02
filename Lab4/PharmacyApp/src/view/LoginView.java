// view/LoginView.java
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginView extends VBox {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;
    private Label messageLabel;

    public LoginView() {
        initializeView();
    }

    private void initializeView() {
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Text title = new Text("Вход в Аптеку");
        title.setFont(Font.font(20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");

        grid.add(new Label("Имя пользователя:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);

        loginButton = new Button("Войти");
        registerButton = new Button("Регистрация");

        HBox buttonBox = new HBox(10, loginButton, registerButton);
        buttonBox.setAlignment(Pos.CENTER);

        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        getChildren().addAll(title, grid, buttonBox, messageLabel);
    }

    // Геттеры
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getLoginButton() { return loginButton; }
    public Button getRegisterButton() { return registerButton; }
    public Label getMessageLabel() { return messageLabel; }
}
