package med.voll.api.domain.patient;

import med.voll.api.domain.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id); // evita duplicados al actualizar
    Page<Patient> findAllByActiveTrue(Pageable pageable);

}