package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.dto.UserDTO;
import service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFormController implements Initializable {

    UserService userService = new UserService();

    @FXML
    private Label lblReEnterPasswordValid;

    @FXML
    private Label lblPasswordValid;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private Label txtLogIn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnterPassword;

    private LogInFormController parentController;

    public void setParentController(LogInFormController parent) {
        this.parentController = parent;
    }


    @FXML
    void LogInOnAction(MouseEvent event) {
        if (parentController != null) {
            parentController.loadUI("/view/sign_in.fxml");
        }
    }

    @FXML
    void reEnterPasswordOnAction(KeyEvent event) {
        String password = txtPassword.getText();
        String reEnterPassword = txtReEnterPassword.getText();

        if(!password.equals(reEnterPassword)){
            lblReEnterPasswordValid.setText("Password Invalid");
        }else{
            lblReEnterPasswordValid.setText("Password Verified");
        }
    }

    @FXML
    void passwordOnAction(KeyEvent event) {
        String password = txtPassword.getText();

        boolean hasLetter = false;
        boolean hasNumber = false;
        boolean hasSymbol = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else {
                hasSymbol = true;
            }
        }

        if (password.length() >= 8 && hasLetter && hasNumber && hasSymbol) {
            lblPasswordValid.setText("Strong Password ✓ ");
            txtReEnterPassword.setEditable(true);
        } else {
            lblPasswordValid.setText("Use letters, numbers, symbols");
            txtReEnterPassword.setEditable(false);
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (!email.endsWith("@gmail.com")) {
            System.out.println("Email must end with @gmail.com");
            return;
        }

        if (firstName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Invalid");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }

        UserDTO userDTO = new UserDTO(firstName,lastName,email,password);
        userService.addUser(userDTO);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        txtReEnterPassword.setEditable(false);
    }
}
