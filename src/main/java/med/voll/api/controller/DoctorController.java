package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.dto.AddressResponseData;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import med.voll.api.domain.doctor.dto.DoctorResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    // Inyección de dependencias por constructor (profesional)
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Transactional
    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponseData> register(@RequestBody @Valid DoctorRegistrationData data) {
        // Crear entidad Doctor desde el DTO
        Doctor doctor = new Doctor(data);

        // Guardar en la base de datos
        doctorRepository.save(doctor);

        // Convertir Address a AddressResponseData
        AddressResponseData addressResponse = new AddressResponseData(doctor.getAddress());

        // Crear DTO de respuesta
        DoctorResponseData response = new DoctorResponseData(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                addressResponse
        );

        return ResponseEntity.ok(response);
    }
}