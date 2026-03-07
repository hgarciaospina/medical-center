package med.voll.api.domain.patient.dto;

import med.voll.api.domain.patient.Patient;

import java.time.LocalDate;

public record PatientSummaryResponse(
        String firstName,
        String lastName,
        String document,
        String email,
        String phone,
        LocalDate birthDate
) {

    // Método de fábrica para mapear de Patient a PatientSummaryResponse
    public static PatientSummaryResponse from(Patient patient) {
        return new PatientSummaryResponse(
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDocument(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getBirthDate()
        );
    }
}