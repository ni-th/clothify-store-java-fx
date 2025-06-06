package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminLoginController implements Initializable {
    public JFXPasswordField txtPassword;
    public JFXTextField txtEmail;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void showAlert(String alert){
        new Alert(Alert.AlertType.ERROR, alert).show();
    }
    private Boolean isFieldsEmpty(){
        if (txtPassword.getText().isEmpty() || txtEmail.getText().isEmpty()){
            return (Boolean) true;
        }
        return (Boolean) false;
    }
    public void btnOnActionLogin(ActionEvent actionEvent) throws IOException, SQLException {

        if (isFieldsEmpty()) {
            showAlert("Field is Required!");
            return;
        }
        if (employeeService.loginUser(txtEmail.getText(), txtPassword.getText())==null){
            showAlert("Admin not Existed!");
            return;
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
        stage.show();
    }

}
