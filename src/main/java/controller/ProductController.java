package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.dto.CartItemDTO;
import model.dto.ProductDTO;
import model.dto.SupplierDTO;
import model.entity.SupplierEntity;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {


    public JFXTextField txtProductID;
    public JFXTextField txtProductName;
    public JFXComboBox cmbProductCategory;
    public JFXTextField txtProductColor;
    public JFXComboBox cmbProductSize;
    public JFXTextField txtProductImageLink;
    public JFXTextField txtProductQty;
    public JFXTextField txtProductCostPrice;
    public JFXTextField txtProductSellingPrice;
    public JFXComboBox cmbProductSuppliers;
    public DatePicker txtProductAddedDate;
    public JFXTextArea txtProductDescription;

    public JFXTextField txtPruductIDDelete;

    public JFXTextField txtUpdateProductID;
    public JFXTextField txtUpdateProductName;
    public JFXComboBox cmbUpdateProductCategory;
    public JFXTextField txtUpdateProductColor;
    public JFXComboBox cmbUpdateProductSize;
    public JFXTextField txtUpdateProductQty;
    public JFXTextField txtUpdateProductImageLink;
    public JFXTextField txtUpdateProductCostPrice;
    public JFXTextField txtUpdateProductSellingPrice;
    public JFXComboBox cmbUpdateProductSuppliers;
    public DatePicker txtUpdateProductAddedDate;
    public JFXTextArea txtUpdateProductDescription;
    public JFXButton btnUpdateProduct;
    public JFXButton btnProductDelete;
    public Label lblSupplierName;

    public TableView tblProduct;
    public TableColumn colProductID;
    public TableColumn colProductName;
    public TableColumn colProductCategory;
    public TableColumn colProductColor;
    public TableColumn colProductSize;
    public TableColumn colProductQty;
    public TableColumn colProductPrice;
    public TableColumn colProductSupplier;
    public TableColumn colProductAddedDate;
    public TableColumn colProductDescription;

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    ObservableList<ProductDTO> productDTOObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        colProductSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colProductAddedDate.setCellValueFactory(new PropertyValueFactory<>("added_date"));
        colProductDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

//        set combo box values
        ObservableList<String> optionsCategory = FXCollections.observableArrayList(
                "Men's",
                "Women's",
                "Kid's",
                "Unisex",
                "Seasonal",
                "Innerwear & Sleepwear",
                "Sportswear",
                "Workwear"

        );
        cmbProductCategory.setItems(optionsCategory);
        cmbUpdateProductCategory.setItems(optionsCategory);

        //        set combo box values
        refreshSupplierList();

        //        set combo box values
        ObservableList<String> optionsSize = FXCollections.observableArrayList("XS", "S", "M", "L", "XL", "XXL");
        cmbProductSize.setItems(optionsSize);
        cmbUpdateProductSize.setItems(optionsSize);
    }
    public void refreshSupplierList(){
        ObservableList<String> optionsSuppliers = FXCollections.observableList(getAllSuppliers());
        cmbProductSuppliers.setItems(optionsSuppliers);
        cmbUpdateProductSuppliers.setItems(optionsSuppliers);
    }

    public void showInfoAlert(String alertContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(alertContent);
        alert.showAndWait();
    }
    public void showInfoAlert(String alertTitle,String alertContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertContent);
        alert.showAndWait();
    }
    private void showErrorAlert(String alert){
        new Alert(Alert.AlertType.ERROR, alert).show();
    }

    private List<String> getAllSuppliers(){
        List<SupplierDTO> all = supplierService.getAll();
        List<String> supplierIDList = new ArrayList<>();
        all.forEach(supplier ->{
            supplierIDList.add(supplier.getId().toString());
        });
        return supplierIDList;

    }





    public void btnOnActionDeleteEmployee(ActionEvent actionEvent) {
    }

    public void btnOnActionProductAdd(ActionEvent actionEvent) {
        String txtProductIDText = txtProductID.getText()!= null ? txtProductID.getText() : "";
        String txtProductNameText = txtProductName.getText()!= null ? txtProductName.getText() : "";
        String txtCategory = cmbProductCategory.getValue()!= null ? cmbProductCategory.getValue().toString() : "";
        String txtProductColorText = txtProductColor.getText()!= null ? txtProductColor.getText() : "";
        String txtProductSizeText = cmbProductSize.getValue()!= null ? cmbProductSize.getValue().toString() : "";
        String txtProductImageLinkText = txtProductImageLink.getText()!= null ? txtProductImageLink.getText() : "";
        String txtProductQtyText = txtProductQty.getText()!= null ? txtProductQty.getText() : "";
        String txtProductCostPriceText = txtProductCostPrice.getText()!= null ? txtProductCostPrice.getText() : "";
        String txtProductSellingPriceText = txtProductSellingPrice.getText()!= null ? txtProductSellingPrice.getText() : "";
        String txtProductSupplier = cmbProductSuppliers.getValue()!= null ? cmbProductSuppliers.getValue().toString() : "";
        LocalDate txtProductAddDate = txtProductAddedDate.getValue()!= null ? LocalDate.parse(txtProductAddedDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) : LocalDate.parse("1000-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String txtProductDescriptionText = txtProductDescription.getText()!= null ? txtProductDescription.getText() : "";
        if (txtProductNameText.isEmpty()
                || txtCategory.isEmpty()
                || txtProductColorText.isEmpty()
                || txtProductSizeText.isEmpty()
                || txtProductImageLinkText.isEmpty()
                || txtProductQtyText.isEmpty()
                || txtProductCostPriceText.isEmpty()
                || txtProductSellingPriceText.isEmpty()
                || txtProductSupplier.isEmpty()
                || txtProductAddDate.isEqual(LocalDate.parse("1000-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                || txtProductDescriptionText.isEmpty())
        {
            showErrorAlert("Field is required");
            return;
        }
        try{
            Integer.parseInt(txtProductQtyText);
            Double.parseDouble(txtProductCostPriceText);
            Double.parseDouble(txtProductSellingPriceText);
        } catch (RuntimeException e) {
            showErrorAlert("Check Your Inputs");
            return;
        }
        Boolean isAdded = productService.add(new ProductDTO(
                null,
                null,
                txtProductNameText,
                txtCategory,
                txtProductColorText,
                txtProductSizeText,
                txtProductImageLinkText,
                Integer.parseInt(txtProductQtyText),
                Double.parseDouble(txtProductCostPriceText),
                Double.parseDouble(txtProductSellingPriceText),
                txtProductSupplier,
                txtProductAddDate,
                txtProductDescriptionText
        ));
        if (isAdded){
            showInfoAlert("Product Added Successfully");
        }else{
            showInfoAlert("Product Not Added");
        }
    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        Boolean isDeleted =txtPruductIDDelete.getText() != null ? productService.deleteByID(Integer.valueOf(txtPruductIDDelete.getText())) : null;
        if (isDeleted){
            showInfoAlert("Product Deleted Successfully");
        }else{
            showInfoAlert("Product Not Found");
        }

    }


    public void btnOnActionGenerateProductReport(ActionEvent actionEvent) {
        productService.generateReport();
    }

    public void OnKeyRelesedUpdateProduct(KeyEvent keyEvent) throws SQLException {
        ProductDTO productDTO = txtUpdateProductID.getText().isEmpty() ? null :  productService.searchById(Integer.valueOf(txtUpdateProductID.getText()));
        if (productDTO == null) setDisableUpdateSection(Boolean.TRUE);

        txtUpdateProductID.setText(String.valueOf(productDTO.getId()));
        txtUpdateProductName.setText(productDTO.getName());
//        cmbUpdateProductCategory.setValue(productDTO.getCategory());
        txtUpdateProductColor.setText(productDTO.getColor());
//          cmbUpdateProductSize.setValue(productDTO.getSize());
        txtUpdateProductQty.setText(productDTO.getQty().toString());
        txtUpdateProductImageLink.setText(productDTO.getImage());
        txtUpdateProductCostPrice.setText(String.valueOf(productDTO.getCost_price()));
        txtUpdateProductSellingPrice.setText(String.valueOf(productDTO.getSelling_price()));
        cmbUpdateProductSuppliers.setValue(productDTO.getSupplier());
//          txtUpdateProductAddedDate.setValue(LocalDate.parse(productDTO.getAdded_date()));
        txtUpdateProductDescription.setText(productDTO.getDescription());
        setDisableUpdateSection(Boolean.FALSE);



    }
    private void setDisableUpdateSection(Boolean b) {
        txtUpdateProductID.setDisable(b);
        txtUpdateProductName.setDisable(b);
        cmbUpdateProductCategory.setDisable(b);
        txtUpdateProductColor.setDisable(b);
        cmbUpdateProductSize.setDisable(b);
        txtUpdateProductQty.setDisable(b);
        txtUpdateProductImageLink.setDisable(b);
        txtUpdateProductCostPrice.setDisable(b);
        txtUpdateProductSellingPrice.setDisable(b);
        cmbUpdateProductSuppliers.setDisable(b);
//        txtUpdateProductAddedDate.setDisable(b);
        txtUpdateProductDescription.setDisable(b);
        btnUpdateProduct.setDisable(b);
    }


    public void btnOnActionProductUpdate(ActionEvent actionEvent) {
        String txtUpdateProductIDText = txtUpdateProductID.getText() != null ? txtUpdateProductID.getText() : "";
        String txtUpdateProductNameText = txtUpdateProductName.getText() != null ? txtUpdateProductName.getText() : "";
        String txtUpdateCategory = cmbUpdateProductCategory.getValue() != null ?  cmbUpdateProductCategory.getValue().toString() : "";
        String txtUpdateProductColorText = txtUpdateProductColor.getText() != null ? txtUpdateProductColor.getText() : "";
        String txtUpdateProductSizeText = cmbUpdateProductSize.getValue() != null ? cmbUpdateProductSize.getValue().toString() : "";
        String txtUpdateProductImageLinkText = txtUpdateProductImageLink.getText() != null ? txtUpdateProductImageLink.getText() : "";
        String txtUpdateProductQtyText = txtUpdateProductQty.getText() != null ? txtUpdateProductQty.getText() : "";
        String txtUpdateProductCostPriceText = txtUpdateProductCostPrice.getText() != null ? txtUpdateProductCostPrice.getText() : "";
        String txtUpdateProductSellingPriceText = txtUpdateProductSellingPrice.getText() != null ? txtUpdateProductSellingPrice.getText() : "";
        String txtUpdateSupplier = cmbUpdateProductSuppliers.getValue() != null ? cmbUpdateProductSuppliers.getValue().toString() : "";
//        String txtUpdateProductAddDate = txtUpdateProductAddedDate.getValue() != null ? txtUpdateProductAddedDate.getValue().toString() : "";
        String txtUpdateProductDescriptionText = txtUpdateProductDescription.getText() != null ? txtUpdateProductDescription.getText() : "";
        if (
                txtUpdateProductNameText.isEmpty()
                        || txtUpdateCategory.isEmpty()
                        || txtUpdateProductColorText.isEmpty()
                        || txtUpdateProductSizeText.isEmpty()
                        || txtUpdateProductImageLinkText.isEmpty()
                        || txtUpdateProductQtyText.isEmpty()
                        || txtUpdateProductCostPriceText.isEmpty()
                        || txtUpdateProductSellingPriceText.isEmpty()
                        || txtUpdateSupplier.isEmpty()
//                        || txtUpdateProductAddDate.isEmpty()
                        || txtUpdateProductDescriptionText.isEmpty()){
            showErrorAlert("Field is required");
            return;
        }
        try{
            Integer.parseInt(txtUpdateProductQtyText);
            Double.parseDouble(txtUpdateProductCostPriceText);
            Double.parseDouble(txtUpdateProductSellingPriceText);
        } catch (RuntimeException e) {
            showErrorAlert("Check Your Inputs");
            return;
        }

        Boolean IsUpdated = productService.update(new ProductDTO(
                Integer.parseInt(txtUpdateProductIDText),
                null,
                txtUpdateProductNameText,
                txtUpdateCategory,
                txtUpdateProductColorText,
                txtUpdateProductSizeText,
                txtUpdateProductImageLinkText,
                Integer.parseInt(txtUpdateProductQtyText),
                Double.parseDouble(txtUpdateProductCostPriceText),
                Double.parseDouble(txtUpdateProductSellingPriceText),
                txtUpdateSupplier,
                null,
                txtUpdateProductDescriptionText
        ));
        if (Boolean.TRUE.equals(IsUpdated)){
            showInfoAlert("Product Updated Successfully");
        }else{
            showInfoAlert("Product Not Updated");
        }
    }

    public void OnActionSupplierCmb(ActionEvent actionEvent) {
        if (cmbProductSuppliers.getValue() != null) {
            SupplierEntity supplier = supplierService.searchByID((Integer.valueOf(cmbProductSuppliers.getValue().toString())) );
            lblSupplierName.setText(supplier.getName()+" - "+supplier.getCompanyName());
        } else {
            System.out.println("");
        }
    }

    public void OnMouseClickedSupplierCmb(MouseEvent mouseEvent) {
        refreshSupplierList();
    }

    public void ListOnSectionChanged(Event event) {
        productDTOObservableList.clear();
        productService.getAll().forEach(product -> {
            productDTOObservableList.add(product);
        });
        tblProduct.setItems(productDTOObservableList);
    }
}
