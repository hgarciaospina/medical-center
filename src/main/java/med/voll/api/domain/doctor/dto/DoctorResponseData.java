package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.address.dto.AddressResponseData;
import med.voll.api.domain.doctor.Specialty;

public record DoctorResponseData(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Specialty specialty,
        AddressResponseData address
) {}