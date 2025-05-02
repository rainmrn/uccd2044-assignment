package app.gui.controller;

import app.model.repo.ProductRepo;
import app.model.repo.UserInfoRepo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private TextField nameTextField;

    @FXML
    private Slider maxNumOfProductSlider;

    @FXML
    private Label maxNumOfProductSliderValue;

    public void onSubmitButtonClick() {
        UserInfoRepo.user.setName(nameTextField.getText());
        UserInfoRepo.generateUserId();
        ProductRepo.setMaxProduct((int) Math.round(maxNumOfProductSlider.getValue()));
        // ConfigRepository.updateConfig();

        UILoader.loadSidebar();
        MainController.goTo(1);
    }

    // Will be called automatically after injecting the FXML fields
    // https://stackoverflow.com/questions/22780369/make-a-label-update-while-dragging-a-slider
    @FXML
    public void initialize() {
        maxNumOfProductSliderValue.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        maxNumOfProductSlider.valueProperty()));

        // mainController = Main.mainController;
    }
}