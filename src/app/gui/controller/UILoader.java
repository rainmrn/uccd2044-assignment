package app.gui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class UILoader {
    private static BorderPane root = new BorderPane();
    private static StackPane mainSection = new StackPane();

    public static Parent getRoot() {
        return root;
    }
    
    public static void initRoot() {
        mainSection.setPadding(new Insets(10, 10, 10, 0));
        root.setCenter(mainSection);
        root.setMinSize(700, 400);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
    
    public static void loadSidebar() {
        try {
            Parent sidebar = FXMLLoader.load(UILoader.class.getResource("/app/gui/view/Sidebar.fxml"));
            root.setLeft(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadSignUpView() {
        try {
            Parent signup = FXMLLoader.load(UILoader.class.getResource("/app/gui/view/SignUp.fxml"));
            mainSection.getChildren().clear();
            mainSection.getChildren().add(signup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHomePageView() {
        try {
            FXMLLoader loader = new FXMLLoader(UILoader.class.getResource("/app/gui/view/Home.fxml"));
            loader.setControllerFactory(n -> HomePageController.getController());
            Parent home = loader.load();

            mainSection.getChildren().clear();
            mainSection.getChildren().add(home);
            HomePageController.getController().updateHomePageMessage();
        } catch (IOException e) {
            System.err.println("Error occurred while loading Home Page.");
            e.printStackTrace();
        }
    }

    public static void loadProductListView(boolean onlyActiveProducts) {
        try {
            FXMLLoader loader = new FXMLLoader(UILoader.class.getResource("/app/gui/view/ProductList.fxml"));
            loader.setControllerFactory(n -> ProductListController.getController());
            Parent productListView = loader.load();

            mainSection.getChildren().clear();
            mainSection.getChildren().add(productListView);
            ProductListController.getController().populateProductListView(onlyActiveProducts);
        } catch (IOException e) {
            System.err.println("Error occurred while loading FXML file");
            e.printStackTrace();
        }
    }

    public static void loadAddProductView() {
        try {
            Parent addProductView = FXMLLoader.load(UILoader.class.getResource("/app/gui/view/AddProduct.fxml"));
            mainSection.getChildren().clear();
            mainSection.getChildren().add(addProductView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
