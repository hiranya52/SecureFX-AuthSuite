package service;

import model.dto.UserDTO;
import model.entity.User;
import password.PasswordUtil;
import repository.UserRepository;

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

        userRepository.addUser(user);


    }

}
