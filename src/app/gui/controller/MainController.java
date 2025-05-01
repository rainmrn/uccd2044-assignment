package app.gui.controller;

import javafx.stage.Stage;

public class MainController {
    private static Stage mainStage = new Stage();

    public static void initMainStage() {
        mainStage.setMinWidth(720);
        mainStage.setMinHeight(450);
        mainStage.setTitle("Welcome to Stockwise.");
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStageTitle(String title) {
        mainStage.setTitle(title);
    }

    public static void goTo(int page) {
        switch (page) {
            case 1:
                UILoader.loadHomePageView();
                setMainStageTitle("Home - Stockwise");
                break;
            case 2:
                UILoader.loadProductListView(false);
                ProductListController.getController().setAction(page);
                setMainStageTitle("View Products - Stockwise");
                break;
            case 3:
                UILoader.loadAddProductView();
                setMainStageTitle("Add Product - Stockwise");
                break;
            case 4:
                UILoader.loadProductListView(true);
                ProductListController.getController().setAction(page);
                setMainStageTitle("Add Stock - Stockwise");
                break;
            case 5:
                UILoader.loadProductListView(true);
                ProductListController.getController().setAction(page);
                setMainStageTitle("Deduct Stock - Stockwise");
                break;
            case 6:
                UILoader.loadProductListView(false);
                ProductListController.getController().setAction(page);
                setMainStageTitle("Discontinue Product - Stockwise");
                break;
            default:
                break;
        }
    }
}
