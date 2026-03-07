package med.voll.api.domain.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.dto.AddressData;

public record PatientRegistrationData(

        @NotBlank(message = "El nombre no puede estar vacío")
        String firstName,

        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,

        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El correo electrónico no tiene un formato válido")
        String email,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(
                regexp = "^(3\\d{9}|\\d{7})$",
                message = "El teléfono debe ser un celular colombiano (10 dígitos iniciando en 3) o un fijo de 7 dígitos"
        )
        String phone,

        @NotBlank(message = "El documento no puede estar vacío")
        @Pattern(
                regexp = "\\d{6,10}",
                message = "El documento debe contener entre 6 y 10 dígitos"
        )
        String document,

        @NotNull(message = "La dirección es obligatoria")
        @Valid
        AddressData address

) {
}