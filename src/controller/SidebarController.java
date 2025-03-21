package controller;

import javafx.fxml.FXML;
import model.repository.ConfigRepository;
import model.repository.ProductRepository;

public class SidebarController {
    private static MainController mainController;

    public static void grabMainController(MainController controller) {
        mainController = controller;
    }

    @FXML
    public void onHomeButtonClick() {
        mainController.goTo(1);
        System.out.println("Home Button Clicked!");
    }

    @FXML
    public void onViewProductButtonClick() {
        mainController.goTo(2);
        System.out.println("View Product Button Clicked!");
    }

    @FXML
    public void onAddStockButtonClick() {
        mainController.goTo(3);
    }

    @FXML
    public void onDeductStockButtonClick() {
        mainController.goTo(4);
    }

    @FXML
    public void onDiscontinueProductClick() {
        mainController.goTo(5);
    }

    @FXML
    public void onResetAppButtonClick() {
        ConfigRepository.configJson.clear();
        ConfigRepository.writeConfigToFile();
        ProductRepository.resetProduct();
        mainController.goTo(8);
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(0);
    }
}
