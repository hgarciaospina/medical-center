package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;

public record DoctorSummaryResponse(
        String firstName,
        String lastName,
        String document,
        Specialty specialty,
        String phone
) {

    // Método de fábrica para mapear de Doctor a DoctorSummaryResponse
    public static DoctorSummaryResponse from(Doctor doctor) {
        return new DoctorSummaryResponse(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getDocument(),
                doctor.getSpecialty(),
                doctor.getPhone()
        );
    }
}