package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.dto.CartItemDTO;
import model.dto.ProductDTO;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CartItemService;
import service.custom.EmailService;
import service.custom.EmployeeService;
import service.custom.ProductService;
import util.ServiceType;
import util.SessionManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BillingController implements Initializable {

    public ListView listView;
    public TableView tblCart;
    public TableColumn colTotal;
    public TableColumn colItemName;
    public TableColumn colItemCode;
    public TableColumn colQty;

    public JFXTextField txtProductID;
    public Label lblName;
    public Label lblStock;
    public Label lblCategory;
    public Label lblSize;
    public Label lblColor;
    public Label lblPrice;
    public JFXComboBox cmbProductList;
    public JFXComboBox cmbProductSize;
    public JFXComboBox cmbProductCategory;
    public JFXButton btnAdd;
    public Label lblTotal;
    public JFXTextField txtOrderID;
    public JFXTextField txtQty;
    public ImageView imgProduct;

    CartItemDTO cartItemDTO;
    ObservableList<CartItemDTO> cartItemDTOObservableList = FXCollections.observableArrayList(); // observable list for load table
    HashMap<Integer,Integer> cartItemMap = new HashMap<>();// cart table
    HashMap<Integer,Integer> cartItemQtyMap = new HashMap<>();// id with qty
    Double totalCost;
    CartItemService cartItemService;
    ProductService productService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        cartItemService = ServiceFactory.getInstance().getServiceType(ServiceType.CARTITEM);
        productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        setTxtOrderID();
        loadCartItemQtyMap();
    }
//    initialize the product qty s
    private void loadCartItemQtyMap(){
        for (ProductDTO itemDTO : productService.getAll()) {
            cartItemQtyMap.put(itemDTO.getId(),itemDTO.getQty());
        }

    }
//    Show Info Alert
    public void showWarningAlert(String alertContent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText(alertContent);
        alert.showAndWait();
    }

//    Show Info Alert
    public void showOnfoAlert(String alertContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText(alertContent);
        alert.showAndWait();
    }

// Set Order ID
    private void setTxtOrderID(){
        txtOrderID.setText(cartItemService.generateOrderID().toString());
    }
//
    private void updateStock(Integer id) throws SQLException {
        Integer qty = cartItemQtyMap.get(id);
        int newQty = qty-Integer.parseInt(txtQty.getText());
        cartItemQtyMap.put(id,newQty);
        if (newQty<0) {
            showWarningAlert("Low Stock");
        }else {
            lblStock.setText(Integer.toString(newQty));
        }

    }

    public void btnOnActionProductAddCart(ActionEvent actionEvent) throws SQLException {
        if (txtQty.getText().isEmpty()){
            showWarningAlert("Qty is Empty");
            return;
        } else if (Integer.parseInt(txtQty.getText()) < 0) {
            showWarningAlert("Qty must be a positive value");
            return;
        }
        cartItemDTOObservableList.clear();
            totalCost=0.0;
            if (cartItemMap.containsKey(Integer.parseInt(txtProductID.getText()))){
                if (cartItemQtyMap.get(Integer.parseInt(txtProductID.getText())) < Integer.parseInt(txtQty.getText())) {
                    showWarningAlert("Low Stock");
                }else{
                    Integer newQty = cartItemMap.get(Integer.parseInt(txtProductID.getText())) + Integer.parseInt(txtQty.getText());
                    cartItemMap.put(Integer.parseInt(txtProductID.getText()), newQty);
                    updateStock(Integer.parseInt(txtProductID.getText()));
                }

            }else{
                if (cartItemQtyMap.get(Integer.parseInt(txtProductID.getText())) < Integer.parseInt(txtQty.getText())) {
                    showWarningAlert("Low Stock");
                }else{
                    cartItemMap.put(Integer.parseInt(txtProductID.getText()),Integer.parseInt(txtQty.getText()));
                    updateStock(Integer.parseInt(txtProductID.getText()));
                }

            }

            //copy to observable array
            cartItemMap.forEach((productID,productQty) ->{
                try {
                    ProductDTO productDTO = productService.searchById(productID);
                    cartItemDTOObservableList.add(new CartItemDTO(
                            Integer.parseInt(txtOrderID.getText()),
                            SessionManager.getInstance().getUser().getId(),
                            productDTO.getName(),
                            productID,
                            productQty,
                            productDTO.getSelling_price()
                    ));
                    totalCost+=productDTO.getSelling_price()*productQty;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            tblCart.setItems(cartItemDTOObservableList);//load to table
            lblTotal.setText(totalCost.toString());

    }

    public void btnOnActionPurchase(ActionEvent actionEvent) {
        cartItemMap.forEach((productID,qty) -> {
            try {
                ProductDTO productDTO = productService.searchById(productID);
                cartItemService.add(
                        new CartItemDTO(
                                Integer.parseInt(txtOrderID.getText()),
                                SessionManager.getInstance().getUser().getId(),
                                productDTO.getName(),
                                productDTO.getId(),
                                qty,
                                productDTO.getSelling_price()
                        )
                );
                productService.updateQty(productID,qty);
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        });

        clear();// clear inputs
        showOnfoAlert("Purchase successful!");
        cartItemService.generateReport(Integer.valueOf(txtOrderID.getText()));
        setTxtOrderID();//when purchase completed. update the order id
    }
    public void btnOnActionCheckAvailability(ActionEvent actionEvent) {
//        EmailService serviceType = ServiceFactory.getInstance().getServiceType(ServiceType.EMAIL);
//        serviceType.sendMail("nimantha.bt@gmail.com", "from clothify", "Hello");
    }

    private void loadProductDetails() throws SQLException {

        String txtProductIDText = txtProductID.getText();
        ProductDTO productDTO = txtProductIDText.isEmpty() ? null :productService.searchById(Integer.parseInt(txtProductIDText));
        if (productDTO == null){
            lblName.setText("---------------------");
            lblStock.setText("---------------------");
            lblCategory.setText("---------------------");
            lblSize.setText("---------------------");
            lblColor.setText("---------------------");
            lblPrice.setText("---------------------");
            btnAdd.setDisable(true);
            txtQty.setDisable(true);
            return;
        }
        lblName.setText(productDTO.getName());
        String image = productDTO.getImage();
        if (!image.isEmpty() && !new Image(productDTO.getImage()).isError()){
            boolean error = new Image(productDTO.getImage()).isError();
            imgProduct.setImage(new Image(productDTO.getImage()));
        }else{
            imgProduct.setImage(new Image("images/no-image.png"));
        }
        lblStock.setText(cartItemQtyMap.get(productDTO.getId()).toString());
        lblCategory.setText(productDTO.getCategory());
        lblSize.setText(productDTO.getSize());
        lblColor.setText(productDTO.getColor());
        lblPrice.setText(productDTO.getSelling_price().toString());
        btnAdd.setDisable(false);
        txtQty.setDisable(false);

    }
//    Product ID Key Relese Event
    public void OnKeyRelesed(KeyEvent keyEvent) throws SQLException {
        loadProductDetails();
    }

//    clear inputs
    private void clear(){
        cartItemMap.clear();
        tblCart.getItems().clear();
        lblName.setText("---------------------");
        lblStock.setText("---------------------");
        lblCategory.setText("---------------------");
        lblSize.setText("---------------------");
        lblColor.setText("---------------------");
        lblPrice.setText("---------------------");
        btnAdd.setDisable(true);
        txtQty.setDisable(true);
        txtProductID.clear();
        txtQty.clear();

    }
    public void btnOnActionClear(ActionEvent actionEvent) {
        clear();
        loadCartItemQtyMap();
        showWarningAlert("Inputs Cleared");
    }
}
