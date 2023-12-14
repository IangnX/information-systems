package ve.ucla.informationsystems.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ve.ucla.informationsystems.dto.SaveUser;
import ve.ucla.informationsystems.exception.InvalidPasswordException;
import ve.ucla.informationsystems.persistence.entity.User;
import ve.ucla.informationsystems.persistence.repository.UserRepository;
import ve.ucla.informationsystems.persistence.util.Role;
import ve.ucla.informationsystems.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user  = new User();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setRole(Role.ROLE_CUSTOMER);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUser newUser) {
        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don´t match");
        }

        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don´t match");
        }
    }
}
