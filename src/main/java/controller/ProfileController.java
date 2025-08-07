package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class ProfileController implements Initializable {

    public JFXTextField txtUpdateName;
    public Label lblID;
    public Label lblEmail;
    public Label lblName;
    public JFXPasswordField txtUpdatePassword1;
    public JFXPasswordField txtUpdatePassword2;
    EmployeeService employeeService;
    EmployeeDTO user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        user = SessionManager.getInstance().getUser();
        setAdminDetails();
    }

    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {
        if (txtUpdatePassword1.getText().isEmpty() || txtUpdatePassword2.getText().isEmpty() || txtUpdateName.getText().isEmpty()){
            showErrorAlert("Field is required.");
            return;
        }
        if (!txtUpdatePassword1.getText().equals(txtUpdatePassword2.getText())){
            showErrorAlert("Passwords do not match. Please try again.");
            return;
        }
        Integer passwordStatus = employeeService.passwordValidator(txtUpdatePassword2.getText());
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
        Boolean update = employeeService.update(
                new EmployeeDTO(
                        user.getId(),
                        txtUpdateName.getText(),
                        user.getEmail(),
                        employeeService.passwordEncrypter(txtUpdatePassword1.getText()),
                        user.getUser_type()
                )
        );
        if (update){
            showInfoAlert("Employee Updated Successfully");
        }else{
            showInfoAlert("Employee Not Updated");
        }
    }

    private void setAdminDetails(){

        if (lblID !=null) lblID.setText(user.getId().toString());
        if (lblEmail !=null) lblEmail.setText(user.getEmail());
        if (lblName !=null){
            lblName.setText(user.getName());
            txtUpdateName.setText(user.getName());
        }
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

}
