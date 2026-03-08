package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.dto.AddressResponseData;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import med.voll.api.domain.doctor.dto.DoctorResponseData;
import med.voll.api.domain.doctor.dto.DoctorSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/doctors/summary")
    public ResponseEntity<List<DoctorSummaryResponse>> listDoctorSummary(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "lastName") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Determinar dirección de ordenación
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // Obtener página de doctores desde el repositorio
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        // Mapear cada Doctor a DTO usando getContent()
        List<DoctorSummaryResponse> summaryList = doctorPage.getContent().stream()
                .map(DoctorSummaryResponse::from) // DTO con método estático de mapeo
                .toList();

        return ResponseEntity.ok(summaryList);
    }
}