import db.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    static public void startIntro(){
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/Intro.fxml"))));
        stage.setTitle("Intro");
        stage.getIcons().add(new Image(getClass().getResource("/images/ico.png").toExternalForm()));
        stage.show();
    }
}
