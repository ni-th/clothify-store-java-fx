package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.dto.EmployeeDTO;
import model.dto.SupplierDTO;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

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

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        set combo box values
        ObservableList<String> options = FXCollections.observableArrayList(
                "cashier",
                "admin"
        );
        cmbEmployeeType.setItems(options);
    }

    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {

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

    public void btnOnActionAddEmployee(ActionEvent actionEvent) throws SQLException {
        if (txtEmpName.getText().isEmpty() ||txtEmpPassword.getText().isEmpty() || txtEmpEmail.getText().isEmpty() || cmbEmployeeType.getValue().toString().isEmpty()){
            showErrorAlert("Field is required");
            return;
        }
//        String txtEmpIDText = txtEmpID.getText();
        String txtEmpNameText = txtEmpName.getText();
        String txtEmpPasswordText = txtEmpPassword.getText();
        String txtEmpEmailText = txtEmpEmail.getText();
        String cmbEmployeeTypeValue = cmbEmployeeType.getValue().toString();
        if (!employeeService.emailValidator(txtEmpEmailText)){
            showErrorAlert("Insert valid Email!");
            return;
        }

        if (employeeService.searchByUserName(txtEmpEmailText) != null){
            showInfoAlert("Email Exist!");
            return;
        }

        Integer passwordStatus = employeeService.passwordValidator(txtEmpPasswordText);
        if (passwordStatus==1){
            showErrorAlert("Password must be at least 8 characters long.");
            return;
        } else if (passwordStatus==2) {
            showErrorAlert("Password must contain at least one uppercase letter (A–Z).");
            return;
        } else if (passwordStatus==3) {
            showErrorAlert("Password must contain at least one lowercase letter (a–z).");
            return;
        } else if (passwordStatus==4) {
            showErrorAlert("Password must contain at least one number (0–9).");
            return;
        } else if (passwordStatus==5) {
            showErrorAlert("Password must contain at least one special character (e.g., ! @ # $ % ^ & *).");
            return;
        }

        Boolean added = employeeService.add(
                new EmployeeDTO(
                        null,
                        txtEmpNameText,
                        txtEmpEmailText,
                        employeeService.passwordEncrypter(txtEmpPasswordText),
                        cmbEmployeeTypeValue
                )
        );
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
        if (!employeeService.emailValidator(txtSuplierEmail.getText())){
            showErrorAlert("Insert valid Email!");
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

    public void btnOnActionGenerateEmployeeReport(ActionEvent actionEvent) {
        employeeService.generateReport();
    }
}
