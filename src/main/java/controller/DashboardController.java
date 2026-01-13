package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.dto.UserDTO;
import service.UserService;

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
        userService.logOutUser(email);


    }

}
