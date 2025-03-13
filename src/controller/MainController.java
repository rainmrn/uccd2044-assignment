package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import app.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.repository.ConfigRepository;
import model.repository.ProductRepository;

public class MainController {
    private BorderPane root = new BorderPane();
    private StackPane mainPanel = new StackPane();
    private Parent productListView;
    private ProductListController productListController;

    public void initSidebar() {
        try {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/view/Sidebar.fxml"));
            root.setLeft(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMainPanel() {
        mainPanel.setPadding(new Insets(10, 10, 10, 0));
        root.setCenter(mainPanel);
    }

    public Parent initRoot() {
        initMainPanel();
        ProductRepository.initProductFile();

        if (ConfigRepository.isExistingUser()) {
            initSidebar();
            goTo(1);
            System.out.println("Existing user.");
        } else {
            loadSignUpView();
            System.out.println("New user.");
        }

        root.setMinSize(700, 400);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return root;
    }

    void loadSignUpView() {
        try {
            Parent signup = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
            mainPanel.getChildren().add(signup);
            Main.currentStage.setTitle("Welcome - StockWise");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void goTo(int page) {
        mainPanel.getChildren().clear();
        ProductRepository.updateProductArray(ProductRepository.getMaxNumOfProduct());
        switch (page) {
            case 1:
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
                    Parent home = loader.load();
                    HomeController homeController = loader.getController();
                    mainPanel.getChildren().addAll(home);
                    homeController.updateLabels();
                    Main.currentStage.setTitle("Home - StockWise");
                } catch (IOException e) {
                    System.err.println("Error occurred while loading FXML");
                    e.printStackTrace();
                }
                break;
            case 2:
                initProductListView(false);
                mainPanel.getChildren().add(productListView);
                productListController.setAction(page);
                Main.currentStage.setTitle("View All Products - StockWise");
                break;
            case 3:
                initProductListView(true);
                mainPanel.getChildren().add(productListView);
                productListController.setAction(page);
                Main.currentStage.setTitle("Add Stock - StockWise");
                break;
            case 4:
                initProductListView(true);
                mainPanel.getChildren().add(productListView);
                productListController.setAction(page);
                Main.currentStage.setTitle("Deduct Stock - StockWise");
                break;
            case 5:
                initProductListView(true);
                mainPanel.getChildren().add(productListView);
                productListController.setAction(page);
                Main.currentStage.setTitle("Discontinue Product - StockWise");
                break;
            case 8:
                Label appResetDoneLabel = new Label("App reset done. Restart to view changes.");
                mainPanel.getChildren().add(appResetDoneLabel);
                Main.currentStage.setTitle("Reset App - StockWise");
                break;
            default:
                break;
        }
    }

    public void initProductListView(boolean onlyActiveProducts) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductList.fxml"));
            productListView = loader.load();
            productListController = loader.getController();
            productListController.populateProductListView(onlyActiveProducts);
        } catch (IOException e) {
            System.err.println("Error occurred while loading FXML file");
            e.printStackTrace();
        }
    }
}
