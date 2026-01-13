package repository;

import db.DBConnection;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    public void addUser(User user) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "INSERT INTO users VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());

        preparedStatement.executeUpdate();

    }


}
