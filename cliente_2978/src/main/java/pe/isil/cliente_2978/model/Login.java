package pe.isil.cliente_2978.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class Login {

    @NotNull(message = "El email es requerido.")
    @NotBlank(message = "El email no debe estar vacio.")
    @Email(message = "Debe proporcionar un email correcto.")
    private String email;

    @NotNull(message = "El password es requerido.")
    @Size(min = 1, message = "El password no debe estar vacío.")
    private String password;
}
