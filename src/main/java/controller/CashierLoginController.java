package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;
import util.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierLoginController implements Initializable {
    public JFXPasswordField txtPassword;
    public JFXTextField txtEmail;

    private static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();
    }
    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    private void showAlert(String alert){
        new Alert(Alert.AlertType.ERROR, alert).show();
    }
    private Boolean isFieldsEmpty(){
        if (txtPassword.getText().isEmpty() || txtEmail.getText().isEmpty()){
            return (Boolean) true;
        }
        return (Boolean) false;
    }
    static public void logout(){
        SessionManager.getInstance().logout();
        stage.close();
    }
    public void btnOnActionLogin(ActionEvent actionEvent) throws IOException, SQLException {

        if (isFieldsEmpty()) {
            showAlert("Field is Required!");
            return;
        }
        System.out.println(employeeService.loginUser(txtEmail.getText(), txtPassword.getText())==null);
        if (employeeService.loginUser(txtEmail.getText(), txtPassword.getText())==null){
            showAlert("Invalid username or password!");
            return;
        }
        SessionManager.getInstance().setUser(employeeService.loginUser(txtEmail.getText(), txtPassword.getText()));

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CashierMain.fxml"))));
        stage.setTitle("Cashier Dashboard");
        stage.getIcons().add(new Image(getClass().getResource("/images/ico.png").toExternalForm()));
        stage.show();
    }
}
