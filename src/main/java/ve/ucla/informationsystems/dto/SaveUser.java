package ve.ucla.informationsystems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveUser implements Serializable {

    @Size(min = 4)
    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotBlank(message = "El campo username es obligatorio")
    private String username;

    @Size(min = 8)
    @NotBlank(message = "El campo password es obligatorio")
    private String password;

    @Size(min = 8)
    private String repeatedPassword;

}
