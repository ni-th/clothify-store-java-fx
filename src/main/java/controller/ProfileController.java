package controller;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.dto.EmployeeDTO;
import util.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private JFXPasswordField txtAdminPassword1;
    @FXML
    private JFXPasswordField txtAdminPassword2;
    public Label lblID;
    public Label lblEmail;
    public Label lblName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdminDetails();
    }

    public void btnOnActionUpdatePassword(ActionEvent actionEvent) {

    }

    private void setAdminDetails(){
        EmployeeDTO user = SessionManager.getInstance().getUser();
        if (lblID !=null) lblID.setText(user.getId().toString());
        if (lblEmail !=null) lblEmail.setText(user.getEmail());
        if (lblName !=null) lblName.setText(user.getName());
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
