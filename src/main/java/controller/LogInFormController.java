package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {

    public void loadUI(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane pane = loader.load();

            // Set parent reference depending on controller type
            Object controller = loader.getController();

            if (controller instanceof SignInFormController) {
                ((SignInFormController) controller).setParentController(this);
            } else if (controller instanceof SignUpFormController) {
                ((SignUpFormController) controller).setParentController(this);
            }

            signContent.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private AnchorPane signContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI("/view/sign_in.fxml");
    }
}

