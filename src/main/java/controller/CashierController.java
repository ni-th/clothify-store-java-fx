package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.dto.CartItemDTO;
import model.dto.ProductDTO;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CartItemService;
import service.custom.EmployeeService;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

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

    CartItemDTO cartItemDTO;
    ObservableList<CartItemDTO> cartItemDTOs = FXCollections.observableArrayList();
    List<CartItemDTO> cartItemDTOList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
    }

    public void btnOnActionProductAddCart(ActionEvent actionEvent) {
        if (cartItemDTO != null){
            cartItemDTOs.add(cartItemDTO);
            cartItemDTOList.add(cartItemDTO);
            tblCart.setItems(cartItemDTOs);
        }
    }

    public void btnOnActionPurchase(ActionEvent actionEvent) {
        CartItemService cartItemService = ServiceFactory.getInstance().getServiceType(ServiceType.CARTITEM);
        cartItemDTOList.forEach(cartItemDTO1 -> {
            cartItemService.add(cartItemDTO1);
            return;
        });
    }

    public void btnOnActionCheckAvailability(ActionEvent actionEvent) {
    }

    public void inputOnActionProductListing(ActionEvent actionEvent) throws SQLException {
        EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

        String txtProductIDText = txtProductID.getText();
        ProductDTO productDTO = productService.searchById(Integer.parseInt(txtProductIDText));
        lblName.setText(productDTO.getName());
        lblStock.setText(productDTO.getQty().toString());
        lblCategory.setText(productDTO.getCategory());
        lblSize.setText(productDTO.getSize());
        lblColor.setText(productDTO.getColor());
        lblPrice.setText(productDTO.getSelling_price().toString());
        btnAdd.setDisable(false);

        cartItemDTO = new CartItemDTO(
                productDTO.getName(),
                productDTO.getId(),
                1,
                productDTO.getSelling_price()
        );



    }
}
