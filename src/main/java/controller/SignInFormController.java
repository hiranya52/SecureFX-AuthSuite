package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;

import java.io.IOException;

public class SignInFormController {

    UserService userService = new UserService();

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

    public boolean verifyPassword(String plainPassword, String storedHash) {
        return BCrypt.checkpw(plainPassword, storedHash);
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        UserDTO user = userService.getUser(email);

        boolean passwordIsValid = verifyPassword(password,user.getPassword());

        if ( email==null || passwordIsValid==false ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIn Failed");
            alert.setHeaderText("Faild");
            alert.setContentText("Check your email and password..");
            alert.showAndWait();
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                Parent root = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.setUser(user);
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                Stage currentStage = (Stage) txtEmail.getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
