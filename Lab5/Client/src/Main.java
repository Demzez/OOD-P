import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Locale;

public class Main extends Application {
    private ObjectOutputStream coos = null;
    private ObjectInputStream cois = null;
    private Socket clientSocket = null;

    private TextField materialField;
    private TextField laborField;
    private TextField additionalLaborField;
    private TextField otherCostsField;
    private TextArea resultArea;
    private Button calculateButton;
    private Button connectButton;
    private Button disconnectButton;
    private Label statusLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор себестоимости продукции");

        // Создание элементов интерфейса
        materialField = new TextField();
        materialField.setPromptText("0.00");

        laborField = new TextField();
        laborField.setPromptText("0.00");

        additionalLaborField = new TextField();
        additionalLaborField.setPromptText("0.00");

        otherCostsField = new TextField();
        otherCostsField.setPromptText("0.00");

        calculateButton = new Button("Рассчитать себестоимость");
        calculateButton.setDisable(true);

        connectButton = new Button("Подключиться к серверу");
        disconnectButton = new Button("Отключиться");
        disconnectButton.setDisable(true);

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);

        statusLabel = new Label("Статус: Не подключено");

        // Обработчики событий
        connectButton.setOnAction(e -> connectToServer());
        disconnectButton.setOnAction(e -> disconnectFromServer());
        calculateButton.setOnAction(e -> calculateCost());

        // Разметка формы
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));

        inputGrid.add(new Label("Затраты на материалы (руб.):"), 0, 0);
        inputGrid.add(materialField, 1, 0);
        inputGrid.add(new Label("Основная зарплата (руб.):"), 0, 1);
        inputGrid.add(laborField, 1, 1);
        inputGrid.add(new Label("Дополнительная зарплата (руб.):"), 0, 2);
        inputGrid.add(additionalLaborField, 1, 2);
        inputGrid.add(new Label("Прочие затраты (руб.):"), 0, 3);
        inputGrid.add(otherCostsField, 1, 3);

        HBox buttonBox = new HBox(10, connectButton, disconnectButton, calculateButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                statusLabel,
                new Label("Введите данные для расчета себестоимости:"),
                inputGrid,
                buttonBox,
                new Label("Результат расчета:"),
                resultArea
        );

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectToServer() {
        try {
            System.out.println("Server connecting....");
            clientSocket = new Socket("127.0.0.1", 5252);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            cois = new ObjectInputStream(clientSocket.getInputStream());

            statusLabel.setText("Статус: Подключено к серверу");
            calculateButton.setDisable(false);
            connectButton.setDisable(true);
            disconnectButton.setDisable(false);

            System.out.println("Connection established....");

        } catch (Exception e) {
            showAlert("Ошибка подключения", "Не удалось подключиться к серверу: " + e.getMessage());
            statusLabel.setText("Статус: Ошибка подключения");
        }
    }

    private void disconnectFromServer() {
        try {
            if (coos != null) {
                coos.writeObject("quit");
                coos.close();
            }
            if (cois != null) cois.close();
            if (clientSocket != null) clientSocket.close();

            statusLabel.setText("Статус: Отключено от сервера");
            calculateButton.setDisable(true);
            connectButton.setDisable(false);
            disconnectButton.setDisable(true);
            resultArea.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateCost() {
        try {
            // Проверяем введенные данные
            double materialCost = parseDoubleField(materialField, "Затраты на материалы");
            double laborCost = parseDoubleField(laborField, "Основная зарплата");
            double additionalLaborCost = parseDoubleField(additionalLaborField, "Дополнительная зарплата");
            double otherCosts = parseDoubleField(otherCostsField, "Прочие затраты");

            // Формируем строку для отправки на сервер
            String dataToSend = String.format(Locale.US, "%.2f %.2f %.2f %.2f",
                    materialCost, laborCost, additionalLaborCost, otherCosts);

            // Отправляем данные на сервер
            coos.writeObject(dataToSend);

            // Получаем результат от сервера
            String serverResponse = (String) cois.readObject();
            resultArea.setText(serverResponse);

        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Пожалуйста, введите корректные числовые значения во все поля.");
        } catch (Exception e) {
            showAlert("Ошибка связи", "Ошибка при обмене данными с сервером: " + e.getMessage());
            disconnectFromServer();
        }
    }

    private double parseDoubleField(TextField field, String fieldName) throws NumberFormatException {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат в поле '" + fieldName + "'");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void stop() {
        disconnectFromServer();
    }
}