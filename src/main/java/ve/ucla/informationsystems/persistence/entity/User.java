package ve.ucla.informationsystems.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ve.ucla.informationsystems.persistence.util.Role;

@Setter
@Getter
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
