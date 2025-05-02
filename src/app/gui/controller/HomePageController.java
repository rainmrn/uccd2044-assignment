package app.gui.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import app.model.repo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomePageController {
    @FXML
    private Label welcomeMessage, currentTime, numOfProducts, numOfActiveProducts, totalStockQuantity,
            totalInventoryValue;

    private static HomePageController controller = new HomePageController();

    private HomePageController() {};

    public static HomePageController getController() {
        return controller;
    }

    // this method is run every time home page loads
    public void updateHomePageMessage() {
        welcomeMessage.setText("Hey, " + UserInfoRepo.user.getName() + ".");

        LocalDateTime time = LocalDateTime.now();
        String formattedTimeString = time
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM));

        currentTime.setText(formattedTimeString);

        numOfProducts.setText(Integer.toString(ProductRepo.getNumOfProduct()));
        numOfActiveProducts.setText(Integer.toString(ProductRepo.getNumOfActiveProduct()));

        totalStockQuantity.setText(Integer.toString(ProductRepo.getTotalStockQuantity()));
        totalInventoryValue.setText(Double.toString(ProductRepo.getTotalInventoryValue()));

    }

    @FXML
    public void initialize() {
        updateHomePageMessage();
    }

}
