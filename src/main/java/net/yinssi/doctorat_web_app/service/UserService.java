
package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(UserRegistrationDto userRegistrationDto);
    boolean checkPassword(UserDetails userDetails, String rawPassword);
    User getCurrentUser();
    User findByEmail(String email);
    User getUserById(long userId);
    void updateUser(UserRegistrationDto userRegistrationDto);
    void deleteUserById(long userId);


}