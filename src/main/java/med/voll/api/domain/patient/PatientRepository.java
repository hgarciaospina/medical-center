package med.voll.api.domain.patient;

import med.voll.api.domain.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
