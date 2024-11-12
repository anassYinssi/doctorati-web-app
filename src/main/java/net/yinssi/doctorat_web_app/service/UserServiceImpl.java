package net.yinssi.doctorat_web_app.service;

import jakarta.persistence.EntityNotFoundException;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.repository.ProposalRepository;
import net.yinssi.doctorat_web_app.repository.UserRepository;
import net.yinssi.doctorat_web_app.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRegistrationDto userRegistrationDto){
        User user = new User(userRegistrationDto.getFirstname(),userRegistrationDto.getLastname(),
                userRegistrationDto.getEmail(),passwordEncoder.encode(userRegistrationDto.getPassword()), userRegistrationDto.getRole());

        return userRepository.save(user);
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(user);
    }



    public boolean checkPassword(UserDetails userDetails, String rawPassword) {
        return passwordEncoder.matches(rawPassword, userDetails.getPassword());
    }

    public String getUserRole(String email) {
        // Fetch user from the database and return their role
        User user = userRepository.findByEmail(email);
        return user != null ? user.getRole() : null;
    }

    public String getFirstNameByEmail(String email) {
        return userRepository.findByEmail(email).getFirstname();
    }

    @Override
    public User getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).get();
    }

    public void updateUser(UserRegistrationDto userRegistrationDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(username);
        if (currentUser != null) {
            currentUser.setFirstname(userRegistrationDto.getFirstname());
            currentUser.setLastname(userRegistrationDto.getLastname());
            currentUser.setEmail(userRegistrationDto.getEmail());

            if (userRegistrationDto.getPassword() != null && !userRegistrationDto.getPassword().isEmpty()) {
                currentUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            }

            userRepository.save(currentUser);
        }
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }


}
