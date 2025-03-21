package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.repository.ProductRepository;
import model.repository.UserRepository;

public class HomeController {
    @FXML
    private Label welcomeMessage, currentTime, numOfProducts, numOfActiveProducts, totalStockQuantity,
            totalInventoryValue;

    public void updateLabels() {
        welcomeMessage.setText("Hey, " + UserRepository.getCurrentUser().getName() + ".");

        LocalDateTime time = LocalDateTime.now();
        String formattedTimeString = time
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM));

        currentTime.setText(formattedTimeString);

        numOfProducts.setText(Integer.toString(ProductRepository.getNumOfProducts()));
        numOfActiveProducts.setText(Integer.toString(ProductRepository.getNumOfActiveProducts()));

        totalStockQuantity.setText(Integer.toString(ProductRepository.getTotalStockQuantity()));
        totalInventoryValue.setText(Double.toString(ProductRepository.getTotalInventoryValue()));

    }

}
