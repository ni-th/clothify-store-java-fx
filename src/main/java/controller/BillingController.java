package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.dto.CartItemDTO;
import model.dto.ProductDTO;
import service.ServiceFactory;
import service.custom.CartItemService;
import service.custom.ProductService;
import util.ServiceType;
import util.SessionManager;

import java.io.File;
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
    ObservableList<CartItemDTO> cartItemDTOObservableList = FXCollections.observableArrayList();
    HashMap<Integer,Integer> cartItemMap = new HashMap<>();
    HashMap<Integer,Integer> cartItemQtyMap = new HashMap<>();
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
        CartItemService cartItemService = ServiceFactory.getInstance().getServiceType(ServiceType.CARTITEM);
        Integer lastID = cartItemService.getLastID();
        if (lastID==null){
            lastID=1000;
        }else {
            lastID+=1;
        }
        txtOrderID.setText(lastID.toString());
    }
//
    private Boolean updateStock(Integer id) throws SQLException {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        ProductDTO productDTO = productService.searchById(id);
        Integer qty = cartItemQtyMap.get(id);
        int newQty = qty-Integer.parseInt(txtQty.getText());
        cartItemQtyMap.put(id,newQty);
        if (newQty<0) {
            showWarningAlert("Low Stock");
            return false;
        }
        lblStock.setText(Integer.toString(newQty));
        return true;
    }

    public void btnOnActionProductAddCart(ActionEvent actionEvent) throws SQLException {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

        if (txtQty.getText().isEmpty()){
            showWarningAlert("Qty is Empty");
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
                cartItemMap.put(Integer.parseInt(txtProductID.getText()),Integer.parseInt(txtQty.getText()));
                updateStock(Integer.parseInt(txtProductID.getText()));
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

            tblCart.setItems(cartItemDTOObservableList);
            lblTotal.setText(totalCost.toString());

    }

    public void btnOnActionPurchase(ActionEvent actionEvent) {
        CartItemService cartItemService = ServiceFactory.getInstance().getServiceType(ServiceType.CARTITEM);
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        cartItemMap.forEach((productID,qty) -> {
            try {
                ProductDTO productDTO = productService.searchById(productID);
                cartItemService.add(new CartItemDTO(Integer.parseInt(txtOrderID.getText()),SessionManager.getInstance().getUser().getId(), productDTO.getName(), productDTO.getId(), qty, productDTO.getSelling_price()));
                productService.updateQty(productID,qty);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        clear();
        showOnfoAlert("Purchased :) ");
        cartItemService.generateReport(Integer.valueOf(txtOrderID.getText()));
        setTxtOrderID();//when purchase completed. update the order id
    }
    public void updateQty(){

    }
    public void btnOnActionCheckAvailability(ActionEvent actionEvent) {
    }

    private void loadProductDetails() throws SQLException {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

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
//        lblStock.setText(productDTO.getQty().toString());
        String image = productDTO.getImage();
        if (!image.isEmpty() && new File(String.valueOf(image)).exists()){
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
        showWarningAlert("Inputs Cleared");
    }
    private void generateBill(){

    }
}
