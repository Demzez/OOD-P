package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Medicine;


public class PharmacyView extends BorderPane implements patterns.Observer {
    private TableView<Medicine> medicineTable;
    private TextField searchField;
    private Button searchButton;
    private Button logoutButton;
    private ComboBox<String> searchTypeComboBox;
    private Label statusLabel;
    private Label userInfoLabel;

    public PharmacyView() {
        initializeView();
    }

    private void initializeView() {
        // Верхняя панель
        HBox topPanel = new HBox(10);
        topPanel.setPadding(new Insets(10));

        userInfoLabel = new Label();
        logoutButton = new Button("Выйти");
        topPanel.getChildren().addAll(userInfoLabel, logoutButton);

        setTop(topPanel);

        // Центральная панель
        VBox centerPanel = new VBox(10);
        centerPanel.setPadding(new Insets(10));

        Text title = new Text("Список лекарств");
        title.setFont(Font.font(18));

        // Панель поиска
        HBox searchPanel = new HBox(10);
        searchField = new TextField();
        searchField.setPromptText("Введите название или категорию...");
        searchButton = new Button("Поиск");

        searchTypeComboBox = new ComboBox<>();
        searchTypeComboBox.getItems().addAll("По названию", "По категории");
        searchTypeComboBox.setValue("По названию");

        searchPanel.getChildren().addAll(searchField, searchTypeComboBox, searchButton);

        // Таблица лекарств
        medicineTable = new TableView<>();
        initializeTable();

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: green;");

        centerPanel.getChildren().addAll(title, searchPanel, medicineTable, statusLabel);
        setCenter(centerPanel);
    }

    private void initializeTable() {
        TableColumn<Medicine, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Medicine, Double> priceCol = new TableColumn<>("Цена");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Medicine, Integer> quantityCol = new TableColumn<>("Количество");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Medicine, String> manufacturerCol = new TableColumn<>("Производитель");
        manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<Medicine, String> typeCol = new TableColumn<>("Тип");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        medicineTable.getColumns().addAll(nameCol, priceCol, quantityCol, manufacturerCol, typeCol);
    }

    public void displayMedicines(java.util.List<Medicine> medicines) {
        ObservableList<Medicine> observableList = FXCollections.observableArrayList(medicines);
        medicineTable.setItems(observableList);
    }

    @Override
    public void update(String message) {
        statusLabel.setText(message);
    }

    // Геттеры
    public TableView<Medicine> getMedicineTable() {
        return medicineTable;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public ComboBox<String> getSearchTypeComboBox() {
        return searchTypeComboBox;
    }

    public Label getUserInfoLabel() {
        return userInfoLabel;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}
