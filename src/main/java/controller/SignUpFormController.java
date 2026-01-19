package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.UserService;

import java.io.IOException;
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
    private Label lblEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnterPassword;

    private LogInFormController parentController;

    public void setParentController(LogInFormController parent) {
        this.parentController = parent;
    }

    @FXML
    void emailOnAction(KeyEvent event) {

        String email = txtEmail.getText();
        if (!email.endsWith("@gmail.com")) {
            lblEmail.setText("Email Invalid");
        }else{
            lblEmail.setText("Email Valid");
        }

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

    public void clearFields(){
        txtFirstName.clear();
        txtLastName.clear();
        txtPassword.clear();
        txtReEnterPassword.clear();
        txtEmail.clear();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (firstName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Invalid");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }

        UserDTO userDTO = new UserDTO(firstName,lastName,email,password);
        userService.addUser(userDTO);
        clearFields();

        UserDTO user = userService.getUser(email);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
