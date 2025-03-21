package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controller.MainController;
import controller.SidebarController;

public class Main extends Application {
    public static Stage currentStage;

    public static MainController mainController = new MainController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            currentStage = stage;
            // mainPanel.getChildren().addAll(welcomeScreen);
            SidebarController.grabMainController(mainController);

            Image icon = new Image("icon.jpg");

            Scene scene = new Scene(mainController.initRoot());
            scene.getStylesheets().add(getClass().getResource("/resources/fonts.css").toExternalForm());

            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setMinWidth(720);
            stage.setMinHeight(450);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}