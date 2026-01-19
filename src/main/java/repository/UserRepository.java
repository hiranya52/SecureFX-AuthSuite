package repository;

import db.DBConnection;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    public User getUser(String email) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Select * From users WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public void logOutUser(String email) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "DELETE FROM users WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1,email);
        preparedStatement.executeUpdate();

    }

}
