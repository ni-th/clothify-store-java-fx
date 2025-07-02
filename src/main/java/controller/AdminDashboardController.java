package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.dto.EmployeeDTO;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;
import util.SessionManager;

import java.net.URL;
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

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

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
    }

    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {

    }

    private void setAdminDetails(){
        EmployeeDTO user = SessionManager.getInstance().getUser();
        lblID.setText(user.getId());
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



    public void btnOnActionAddEmployee(ActionEvent actionEvent) {
        if (txtEmpID.getText().isEmpty() || txtEmpName.getText().isEmpty() ||txtEmpPassword.getText().isEmpty() || txtEmpEmail.getText().isEmpty() || cmbEmployeeType.getValue().toString().isEmpty()){
            showErrorAlert("Field is required");
            return;
        }
        String txtEmpIDText = txtEmpID.getText();
        String txtEmpNameText = txtEmpName.getText();
        String txtEmpPasswordText = txtEmpPassword.getText();
        String txtEmpEmailText = txtEmpEmail.getText();
        String cmbEmployeeTypeValue = cmbEmployeeType.getValue().toString();




        Boolean added = employeeService.add(new EmployeeDTO(txtEmpIDText, txtEmpNameText, txtEmpEmailText, txtEmpPasswordText, cmbEmployeeTypeValue));
        if (added){
            showInfoAlert("Employee Added Successfully");
        }else{
            showInfoAlert("Employee Not Added");
        }

    }

    public void btnOnActionAddSuplier(ActionEvent actionEvent) {
    }

    public void btnOnActionDeleteEmployee(ActionEvent actionEvent) {
    }
}
