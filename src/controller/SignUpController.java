package controller;

import app.Main;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.repository.ConfigRepository;
import model.repository.ProductRepository;
import model.repository.UserRepository;

public class SignUpController {
    private MainController mainController;

    @FXML
    private TextField nameTextField;

    @FXML
    private Slider maxNumOfProductSlider;

    @FXML
    private Label maxNumOfProductSliderValue;

    public void onSubmitButtonClick() {
        UserRepository.createUser(nameTextField.getText());
        ProductRepository.setMaxNumOfProduct((int) maxNumOfProductSlider.getValue());
        ConfigRepository.updateConfig();

        mainController.initSidebar();
        mainController.goTo(1);
    }

    // Will be called automatically after injecting the FXML fields
    // https://stackoverflow.com/questions/22780369/make-a-label-update-while-dragging-a-slider
    @FXML
    public void initialize() {
        maxNumOfProductSliderValue.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        maxNumOfProductSlider.valueProperty()));

        mainController = Main.mainController;
    }
}
