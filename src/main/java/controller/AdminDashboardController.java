package controller;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {

    }

    private void setAdminDetails(){
        EmployeeDTO user = SessionManager.getInstance().getUser();
        lblID.setText(user.getId());
        lblEmail.setText(user.getEmail());
        lblName.setText(user.getName());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdminDetails();
        System.out.println(employeeService.getAll());
    }
}
