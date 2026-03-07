package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.dto.AddressResponseData;

public record PatientResponseData(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String document,                // sí, si quieres mostrarlo
        String birthDate,               // fecha de nacimiento
        AddressResponseData address     // sí, si quieres mostrar la dirección
) {}