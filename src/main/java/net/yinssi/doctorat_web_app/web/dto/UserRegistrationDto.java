package net.yinssi.doctorat_web_app.web.dto;

import lombok.Getter;
import lombok.Setter;


public class UserRegistrationDto {

    @Setter
    @Getter
    private int id;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String role;

}
