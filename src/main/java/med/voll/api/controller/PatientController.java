package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import med.voll.api.domain.patient.dto.PatientResponseData;
import med.voll.api.domain.address.dto.AddressResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponseData> register(@RequestBody @Valid PatientRegistrationData data) {
        // Crear entidad Patient desde el DTO
        Patient patient = new Patient(data);

        // Guardar en la base de datos
        patientRepository.save(patient);

        // Convertir Address a AddressResponseData
        AddressResponseData addressResponse = new AddressResponseData(patient.getAddress());

        // Crear DTO de respuesta
        PatientResponseData response = new PatientResponseData(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument(),
                patient.getBirthDate(),
                addressResponse
        );

        return ResponseEntity.ok(response);
    }
}