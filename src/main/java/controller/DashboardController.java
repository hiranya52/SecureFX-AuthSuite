package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.UserService;

import java.io.IOException;

public class DashboardController {

    UserService userService = new UserService();

    @FXML
    private Label lblName;

    String email = "";

    public void setUser(UserDTO user) {
        lblName.setText(user.getFirstName());
        email=user.getEmail();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_form.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
