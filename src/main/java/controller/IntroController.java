package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {
    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    static private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeService.creatAdmin();
        stage = new Stage();
    }
    static public void closeWindow(){
        stage.close();
    }
    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"))));
        stage.setTitle("Admin Login");
        stage.getIcons().add(new Image(getClass().getResource("/images/ico.png").toExternalForm()));
        stage.show();


    }
    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CashierLogin.fxml"))));
        stage.setTitle("Cashier Login");
        stage.getIcons().add(new Image(getClass().getResource("/images/ico.png").toExternalForm()));
        stage.show();
    }


}
