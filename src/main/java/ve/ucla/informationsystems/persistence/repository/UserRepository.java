package ve.ucla.informationsystems.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.ucla.informationsystems.persistence.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
