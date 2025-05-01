package app.gui.controller;

import javafx.fxml.FXML;

public class SidebarController {

    @FXML
    public void onHomeButtonClick() {
        MainController.goTo(1);
    }

    @FXML
    public void onViewProductButtonClick() {
        MainController.goTo(2);
    }

    @FXML
    public void onAddProductButtonClick() {
        MainController.goTo(3);
    }

    @FXML
    public void onAddStockButtonClick() {
        MainController.goTo(4);
    }

    @FXML
    public void onDeductStockButtonClick() {
        MainController.goTo(5);
    }

    @FXML
    public void onDiscontinueProductClick() {
        MainController.goTo(6);
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(0);
    }
}