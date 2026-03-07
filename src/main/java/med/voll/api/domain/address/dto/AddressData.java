package med.voll.api.domain.address.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressData(

        @NotBlank(message = "El departamento no puede estar vacío")
        String state,

        @NotBlank(message = "La ciudad no puede estar vacía")
        String city,

        @NotBlank(message = "La calle no puede estar vacía")
        String street,

        @NotBlank(message = "El número no puede estar vacío")
        String number,

        String complement

) {
}