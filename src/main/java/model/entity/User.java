package model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
