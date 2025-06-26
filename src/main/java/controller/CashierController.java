package controller;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

    public ListView listView;

    Button btn = new Button("Hello");
    Label lbl = new Label("Lable");
    Image img = new Image("https://www.nolimit.lk/_next/image?url=https%3A%2F%2Fcdn.greencloudpos.com%2Fnolimit.lk%2Fproduct%2FHUF%26DEEWomen%27sNotchNeckT-ShirtDarkTeal-2-1747807169093-DSC07147.jpg%3Fwidth%3D600&w=750&q=75");

    HBox hbox = new HBox(10);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView imageView1 = new ImageView(img);
        imageView1.setFitHeight(100);
        imageView1.setPreserveRatio(true);

        hbox.getChildren().addAll(imageView1,lbl,btn);
        hbox.setAlignment(Pos.CENTER);
        listView.getItems().add(hbox);
        listView.getItems().add(hbox);
    }
}
