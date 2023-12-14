package ve.ucla.informationsystems.service;



import ve.ucla.informationsystems.dto.SaveUser;
import ve.ucla.informationsystems.persistence.entity.User;

import java.util.Optional;

public interface UserService {

    User registerOneCustomer(SaveUser newUser);

    Optional<User> findByUsername(String username);
}
