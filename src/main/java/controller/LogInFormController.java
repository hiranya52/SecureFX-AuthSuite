package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogInFormController {

    //-------------------Load UI-------------------//
    private void loadUI(String path) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(path));
            signContent.getChildren().clear();
            signContent.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane signContent;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label txtSignUp;

    @FXML
    void SignUpOnAction(MouseEvent event) {
        loadUI("/view/sign_up.fxml");
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

}
