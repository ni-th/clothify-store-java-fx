package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.dto.EmployeeDTO;
import util.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {
    public AnchorPane root;
    public Label lblUserHeding;
    public Label lblUserTypeHeding;

    //FX Parent Loading
    URL empployeeManage;
    Parent loadEmployee;

    URL productManage;
    Parent loadProduct;

    URL profile;
    Parent loadProfile;

    URL billing;
    Parent loadBilling;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdminDetails();
        IntroController.closeWindow();
    }
    private void setAdminDetails(){
        EmployeeDTO user = SessionManager.getInstance().getUser();
        lblUserHeding.setText(user.getName());
        lblUserTypeHeding.setText(user.getUser_type());

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


    public void btnOnActionBillingFormLoad(ActionEvent actionEvent) throws IOException {
        if (billing == null){
            billing = this.getClass().getResource("/view/component/Billing.fxml");
        }
        assert productManage != null;
        if (loadBilling == null){
            loadBilling = FXMLLoader.load(billing);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(loadBilling);
    }

    public void btnOnActionProductMngFormLoad(ActionEvent actionEvent) throws IOException {
        if (productManage == null){
            productManage = this.getClass().getResource("/view/component/ProductManagement.fxml");
        }
        assert productManage != null;
        if (loadProduct == null){
            loadProduct = FXMLLoader.load(productManage);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(loadProduct);
    }

    public void btnOnActionEmployeeMngFormLoad(ActionEvent actionEvent) throws IOException {
        if (empployeeManage == null){
            empployeeManage = this.getClass().getResource("/view/component/EmployeeManagement.fxml");
        }
        assert empployeeManage != null;
        if (loadEmployee == null){
            loadEmployee = FXMLLoader.load(empployeeManage);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(loadEmployee);
    }

    public void btnOnActionProfileFormLoad(ActionEvent actionEvent) throws IOException {
        if (profile == null){
            profile = this.getClass().getResource("/view/component/Profile.fxml");
        }
        assert profile != null;
        if (loadProfile == null){
            loadProfile = FXMLLoader.load(profile);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(loadProfile);
    }

    public void btnOnActionLogout(ActionEvent actionEvent) {
        AdminLoginController.logout();
    }
}
