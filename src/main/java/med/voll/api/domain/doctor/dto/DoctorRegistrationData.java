package med.voll.api.domain.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.address.dto.AddressData;
import med.voll.api.domain.doctor.Specialty;

public record DoctorRegistrationData(

        @NotBlank(message = "El nombre no puede estar vacío")
        String firstName,

        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,

        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "El teléfono no puede estar vacío")
        String phone,

        @NotBlank(message = "El documento profesional no puede estar vacío")
        @Pattern(regexp = "\\d{6,10}", message = "El documento debe contener entre 6 y 10 dígitos")
        String document,

        @NotNull(message = "La especialidad es obligatoria")
        Specialty specialty,

        @NotNull(message = "La dirección es obligatoria")
        @Valid
        AddressData addressData

) {}