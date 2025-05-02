package app.gui.controller;

import app.model.entity.HairDryer;
import app.model.entity.Product;
import app.model.entity.Refrigerator;
import app.model.entity.TV;
import app.model.repo.ProductRepo;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class AddProductController {
    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Label extraLabel1, extraLabel2, extraLabel3, successMessage;

    @FXML
    private TextField itemNumberTextField, nameTextField, quantityTextField, priceTextField, extraTextField1, extraTextField2, extraTextField3;

    private String type, itemNumber, name, extra1, extra2;

    private int quantity, extra3;

    private double price;

    public void onAddProductBtnClick() {
        itemNumber = itemNumberTextField.getText();
        name = nameTextField.getText();
        quantity = Integer.parseInt(quantityTextField.getText());
        price = Double.parseDouble(priceTextField.getText());
        extra1 = extraTextField1.getText();
        extra2 = extraTextField2.getText();
        extra3 = Integer.parseInt(extraTextField3.getText());

        Product newProduct;

        if (type == "Refrigerator") {
            newProduct = new Refrigerator(name, price, quantity, itemNumber, extra1, extra2, extra3);
        } else if (type == "TV") {
            newProduct = new TV(name, price, quantity, itemNumber, extra1, extra2, extra3);
        } else {
            newProduct = new HairDryer(name, price, quantity, itemNumber, extra1, extra2, extra3);
        }

        ProductRepo.addProduct(newProduct);
        successMessage.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(_ -> successMessage.setVisible(false));
        pause.play();
        clearFields();
    }

    public void clearFields() {
        itemNumberTextField.setText("");
        nameTextField.setText("");
        quantityTextField.setText("");
        priceTextField.setText("");
        extraTextField1.setText("");
        extraTextField2.setText("");
        extraTextField3.setText("");
    }

    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList("Refrigerator", "TV", "Hair Dryer"));
        typeComboBox.setOnAction(_ -> {
            type = typeComboBox.getValue();

            if (type == "Refrigerator") {
                extraLabel1.setText("Door Design");
                extraLabel2.setText("Color");
                extraLabel3.setText("Capacity (L)");
            } else if (type == "TV") {
                extraLabel1.setText("Screen Type");
                extraLabel2.setText("Resolution");
                extraLabel3.setText("Size (inches)");
            } else if (type == "Hair Dryer") {
                extraLabel1.setText("Nozzle Type");
                extraLabel2.setText("Color");
                extraLabel3.setText("Power (watts)");
            }
        });
    }
}
