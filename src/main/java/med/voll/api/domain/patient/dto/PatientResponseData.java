package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.dto.AddressResponseData;

import java.time.LocalDate;

public record PatientResponseData(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String document,
        LocalDate birthDate,
        AddressResponseData address
) {}