package app.gui;

import app.gui.controller.MainController;
import app.gui.controller.UILoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StockManagementGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            UILoader.initRoot();
            UILoader.loadSignUpView();
            Scene scene = new Scene(UILoader.getRoot());

            MainController.initMainStage();
            MainController.getMainStage().setScene(scene);
            MainController.getMainStage().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
