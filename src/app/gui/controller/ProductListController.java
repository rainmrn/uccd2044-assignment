package app.gui.controller;

import app.model.entity.Product;
import app.model.entity.Refrigerator;
import app.model.entity.TV;
import app.model.repo.ProductRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ProductListController {
    private Product selectedProduct;

    private static ProductListController controller = new ProductListController();

    private ProductListController() {};

    public static ProductListController getController() {
        return controller;
    }

    @FXML
    private ListView<Product> productListView;

    @FXML
    private Label detailsProductName, detailsType, detailsItemNum, detailsStock, detailsPrice, detailsStatus;

    @FXML
    private Label extraDetail1, extraDetail2, extraDetail3, extraDetail1Value, extraDetail2Value, extraDetail3Value;

    @FXML
    private Label actionLabel;

    @FXML
    private Button actionButton;

    @FXML
    private TextField actionInputField;

    @FXML
    private HBox actionHBox;

    @FXML
    private Separator separator;

    public void populateProductListView(boolean onlyActiveProducts) {
        if (onlyActiveProducts) {
            ProductRepo.updateActiveProductList();
            productListView.setItems(ProductRepo.activeProductList);
        } else {
            productListView.setItems(ProductRepo.productList);
        }

        // set cell name of product in the list view
        productListView.setCellFactory(_ -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (empty || product == null) {
                    setText(null);
                } else {
                    setText(product.getName());
                }
            }
        });
    }

    public void onItemSelect() {
        if (productListView.getSelectionModel().getSelectedItem() == null) {
            return; // do nothing if nothing is selected, duh
        }
        updateProductDetails();
        actionHBox.setVisible(true);
    }

    private void updateProductDetails() {
        selectedProduct = productListView.getSelectionModel().getSelectedItem();
        detailsType.setText(selectedProduct.getType());
        detailsItemNum.setText(selectedProduct.getItemNumber());
        detailsProductName.setText(selectedProduct.getName());
        detailsStock.setText(Integer.toString(selectedProduct.getQuantity()));
        detailsPrice.setText(Double.toString(selectedProduct.getPrice()));
        detailsStatus.setText(selectedProduct.getStatusText());

        separator.setVisible(true);

        if (selectedProduct instanceof Refrigerator) {
            Refrigerator selectedRefrigerator = (Refrigerator) selectedProduct;
            extraDetail1.setText("Door design");
            extraDetail2.setText("Color");
            extraDetail3.setText("Capacity (litres)");

            extraDetail1Value.setText(selectedRefrigerator.getDoorDesign());
            extraDetail2Value.setText(selectedRefrigerator.getColor());
            extraDetail3Value.setText(Double.toString(selectedRefrigerator.getCapacity()));

        } else if (selectedProduct instanceof TV) {
            TV selectedTV = (TV) selectedProduct;
            extraDetail1.setText("Screen type");
            extraDetail2.setText("Resolution");
            extraDetail3.setText("Display size (inches)");

            extraDetail1Value.setText(selectedTV.getScreenType());
            extraDetail2Value.setText(selectedTV.getResolution());
            extraDetail3Value.setText(Double.toString(selectedTV.getDisplaySize()));
        }
    }

    // Method to clear the selection
    public void clearSelection() {
        productListView.getSelectionModel().clearSelection();
        clearProductDetails();
    }

    private void clearProductDetails() {
        // Clear all text fields
        detailsProductName.setText("Product Name");
        detailsType.setText("");
        detailsItemNum.setText("");
        detailsStock.setText("");
        detailsPrice.setText("");
        detailsStatus.setText("");

        // Clear extra details
        extraDetail1.setText("");
        extraDetail2.setText("");
        extraDetail3.setText("");
        extraDetail1Value.setText("");
        extraDetail2Value.setText("");
        extraDetail3Value.setText("");

        // Hide separator
        separator.setVisible(false);

        // Clear selected product reference
        selectedProduct = null;
    }

    public void setAction(int page) {
        switch (page) {
            case 2:
                actionHBox.getChildren().clear();
            case 4: // Add stock
                actionLabel.setText("Amount of stock to add:");
                actionButton.setOnAction(_ -> {
                    if (selectedProduct != null) {
                        int amountToAdd = Integer.parseInt(actionInputField.getText());
                        selectedProduct.addQuantity(amountToAdd);
                        actionInputField.clear();
                        clearSelection();
                    }
                });
                break;
            case 5:
                actionLabel.setText("Amount of stock to deduct:");
                actionButton.setOnAction(_ -> {
                    if (selectedProduct != null) {
                        int amountToDeduct = Integer.parseInt(actionInputField.getText());
                        selectedProduct.deductQuantity(amountToDeduct);
                        actionInputField.clear();
                        clearSelection();
                    }
                });
                break;
            case 6:
                if (selectedProduct != null) {
                    actionLabel.setText(selectedProduct.getStatus() ? "Discontinue this product?" : "Reactivate this product?");
                    actionHBox.getChildren().remove(actionInputField);
                    actionButton.setOnAction(_ -> {
                        selectedProduct.setStatus(selectedProduct.getStatus() ? false : true);
                        actionInputField.clear();
                        clearSelection();
                    });
                }
                
                break;
            default:
                break;
        }
    }

}