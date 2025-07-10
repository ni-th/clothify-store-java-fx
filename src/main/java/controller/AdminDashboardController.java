package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.dto.EmployeeDTO;
import model.dto.ProductDTO;
import model.dto.SupplierDTO;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;
import util.SessionManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    public JFXPasswordField txtAdminPassword1;
    public JFXPasswordField txtAdminPassword2;

    public Label lblID;
    public Label lblEmail;
    public Label lblName;

    public JFXPasswordField txtEmpPassword;
    public JFXTextField txtEmpID;
    public JFXComboBox cmbEmployeeType;
    public JFXTextField txtEmpName;
    public JFXTextField txtSuplierName;
    public JFXTextField txtSuplierCompany;
    public JFXTextField txtSuplierEmail;
    public JFXTextField txtSuplierID;
    public JFXTextField txtEmployeeDelete;
    public JFXTextField txtEmpEmail;

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



    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdminDetails();
        System.out.println(employeeService.getAll());

//        set combo box values
        ObservableList<String> options = FXCollections.observableArrayList(
                "cashier",
                "admin"
        );
        cmbEmployeeType.setItems(options);

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

        //        set combo box values
        ObservableList<String> optionsSuppliers = FXCollections.observableList(getAllSuppliers());

        cmbProductSuppliers.setItems(optionsSuppliers);

        //        set combo box values
        ObservableList<String> optionsSize = FXCollections.observableArrayList("XS", "S", "M", "L", "XL", "XXL");
        cmbProductSize.setItems(optionsSize);
    }

    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {

    }

    private void setAdminDetails(){
        EmployeeDTO user = SessionManager.getInstance().getUser();
        lblID.setText(user.getId().toString());
        lblEmail.setText(user.getEmail());
        lblName.setText(user.getName());
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

    public void btnOnActionAddEmployee(ActionEvent actionEvent) throws SQLException {
        if (txtEmpName.getText().isEmpty() ||txtEmpPassword.getText().isEmpty() || txtEmpEmail.getText().isEmpty() || cmbEmployeeType.getValue().toString().isEmpty()){
            showErrorAlert("Field is required");
            return;
        }
        String txtEmpIDText = txtEmpID.getText();
        String txtEmpNameText = txtEmpName.getText();
        String txtEmpPasswordText = txtEmpPassword.getText();
        String txtEmpEmailText = txtEmpEmail.getText();
        String cmbEmployeeTypeValue = cmbEmployeeType.getValue().toString();

        if (employeeService.searchByUserName(txtEmpEmailText) != null){
            showInfoAlert("Email Exist!");
            return;
        }
        Boolean added = employeeService.add(new EmployeeDTO(null, txtEmpNameText, txtEmpEmailText, txtEmpPasswordText, cmbEmployeeTypeValue));
        if (added){
            showInfoAlert("Employee Added Successfully");
        }else{
            showInfoAlert("Employee Not Added");
        }

    }

    public void btnOnActionAddSuplier(ActionEvent actionEvent) throws SQLException {
        if (txtSuplierName.getText().isEmpty() ||txtSuplierCompany.getText().isEmpty() || txtSuplierEmail.getText().isEmpty()){
            showErrorAlert("Field is required");
            return;
        }
        if (supplierService.searchByUserName(txtSuplierEmail.getText()) != null){
            showInfoAlert("Email Exist!");
            return;
        }
        String txtSuplierIDText = txtSuplierID.getText();
        String txtSuplierNameText = txtSuplierName.getText();
        String txtSuplierCompanyText = txtSuplierCompany.getText();
        String txtSuplierEmailText = txtSuplierEmail.getText();

        Boolean added = supplierService.add(new SupplierDTO(null, txtSuplierNameText, txtSuplierCompanyText, txtSuplierEmailText));
        if (added){
            showInfoAlert("Supplier Added Successfully");
        }else{
            showInfoAlert("Supplier Not Added");
        }
    }

    public void btnOnActionDeleteEmployee(ActionEvent actionEvent) {
    }

    public void btnOnActionProductAdd(ActionEvent actionEvent) {
        String txtProductIDText = txtProductID.getText();
        String txtProductNameText = txtProductName.getText();
        String txtCategory = cmbProductCategory.getValue().toString();
        String txtProductColorText = txtProductColor.getText();
        String txtProductSizeText = cmbProductSize.getValue().toString();
        String txtProductImageLinkText = txtProductImageLink.getText();
        String txtProductQtyText = txtProductQty.getText();
        String txtProductCostPriceText = txtProductCostPrice.getText();
        String txtProductSellingPriceText = txtProductSellingPrice.getText();
        String txtUserType = cmbProductSuppliers.getValue().toString();
        String txtProductAddDate = txtProductAddedDate.getValue().toString();
        String txtProductDescriptionText = txtProductDescription.getText();
        if (txtProductNameText.isEmpty() || txtCategory.isEmpty() || txtProductColorText.isEmpty() ||
                txtProductSizeText.isEmpty() || txtProductImageLinkText.isEmpty() || txtProductQtyText.isEmpty() ||
                txtProductCostPriceText.isEmpty() || txtProductSellingPriceText.isEmpty() || txtUserType.isEmpty() ||
                txtProductAddDate.isEmpty() || txtProductDescriptionText.isEmpty()){

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
                txtUserType,
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
    }
}
