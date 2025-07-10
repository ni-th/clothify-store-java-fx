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


//    Button btn = new Button("Hello");
//    Label lbl = new Label("Lable");
//    Image img = new Image("https://www.nolimit.lk/_next/image?url=https%3A%2F%2Fcdn.greencloudpos.com%2Fnolimit.lk%2Fproduct%2FHUF%26DEEWomen%27sNotchNeckT-ShirtDarkTeal-2-1747807169093-DSC07147.jpg%3Fwidth%3D600&w=750&q=75");
//
//    HBox hbox = new HBox(10);
    CartItemDTO cartItemDTO;
    ObservableList<CartItemDTO> cartItemDTOs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ImageView imageView1 = new ImageView(img);
//        imageView1.setFitHeight(100);
//        imageView1.setPreserveRatio(true);
//
//        hbox.getChildren().addAll(imageView1,lbl,btn);
//        hbox.setAlignment(Pos.CENTER);
//        listView.getItems().add(hbox);
//        listView.getItems().add(hbox);

        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("selling_price"));


    }

    public void btnOnActionProductAddCart(ActionEvent actionEvent) {
        if (cartItemDTO != null){
            cartItemDTOs.add(cartItemDTO);
            tblCart.setItems(cartItemDTOs);
        }
    }

    public void btnOnActionPurchase(ActionEvent actionEvent) {
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
