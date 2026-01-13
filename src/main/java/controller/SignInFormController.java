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

public class SignInFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label txtSignUp;

    private LogInFormController parentController;

    public void setParentController(LogInFormController parent) {
        this.parentController = parent;
    }

    @FXML
    void SignUpOnAction(MouseEvent event) {
        if (parentController != null) {
            parentController.loadUI("/view/sign_up.fxml");
        }
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

}
