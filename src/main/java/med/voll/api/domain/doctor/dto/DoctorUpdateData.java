package med.voll.api.domain.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.dto.AddressData;

/**
 * DTO para actualizar la información de un Doctor.
 * Incluye nombre, apellido, teléfono y dirección.
 */
public record DoctorUpdateData(
        @NotNull(message = "El ID del doctor es obligatorio")
        Long id,
        @NotBlank(message = "El nombre no puede estar vacío")
        String firstName,

        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(regexp = "^(3\\d{9}|\\d{7})$", message = "El teléfono debe ser un celular colombiano (10 dígitos iniciando en 3) o un fijo de 7 dígitos")
        String phone,

        @NotNull(message = "La dirección es obligatoria")
        @Valid
        AddressData addressData
) {}