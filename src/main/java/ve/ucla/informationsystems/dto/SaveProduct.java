package ve.ucla.informationsystems.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
public class SaveProduct implements Serializable {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @DecimalMin(value = "0.01", message = "El campo precio debe ser mayor a 0")
    @NotNull(message = "El campo precio es obligatorio")
    private BigDecimal price;

}
