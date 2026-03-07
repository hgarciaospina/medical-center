package med.voll.api.domain.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.dto.AddressData;
import med.voll.api.domain.doctor.Specialty;

/**
 * Represents the data required to register a doctor in the system.
 */
public record DoctorRegistrationData(

        @NotBlank(message = "El nombre no puede estar vacío")
        String firstName,               // Doctor's first name

        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,                // Doctor's last name

        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,                   // Doctor's email address

        @NotBlank(message = "El teléfono no puede estar vacío")
        String phone,                   // Contact phone number

        @NotBlank(message = "El documento profesional no puede estar vacío")
        @Pattern(regexp = "\\d{4,6}", message = "El documento debe contener entre 4 y 6 dígitos")
        String document,                // Professional document/license number

        @NotNull(message = "La especialidad es obligatoria")
        Specialty specialty,            // Medical specialty

        @NotNull(message = "La dirección es obligatoria")
        @Valid
        AddressData addressData        // Address information
) {
}