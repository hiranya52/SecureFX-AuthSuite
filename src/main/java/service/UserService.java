package service;

import model.dto.UserDTO;
import model.entity.User;
import password.PasswordUtil;
import repository.UserRepository;

import java.sql.SQLException;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public void addUser(UserDTO userDTO) {

        String hashedPassword = PasswordUtil.hashPassword(userDTO.getPassword());

        User user = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                hashedPassword
        );

        try {
            userRepository.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDTO getUser(String email) {
        try {
            User user = userRepository.getUser(email);

            return new UserDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOutUser(String email) {
        try {
            userRepository.logOutUser(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
