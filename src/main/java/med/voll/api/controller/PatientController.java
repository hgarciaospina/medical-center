package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import med.voll.api.domain.patient.dto.PatientResponseData;
import med.voll.api.domain.patient.dto.PatientSummaryResponse;
import med.voll.api.domain.address.dto.AddressResponseData;
import med.voll.api.domain.patient.dto.PatientUpdateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de pacientes.
 * Permite:
 * - Registrar nuevos pacientes.
 * - Listar pacientes con resumen (con paginación y ordenación).
 */
@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientRepository patientRepository;

    // Inyección de dependencia por constructor (profesional)
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Registro de un nuevo paciente.
     *
     * @param data DTO con los datos de registro del paciente.
     * @return DTO de respuesta con la información del paciente registrado.
     */
    @Transactional
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

    /**
     * Listar resumen de pacientes con paginación y ordenación.
     *
     * @param page    Número de página (0-based), por defecto 0.
     * @param size    Cantidad de registros por página, por defecto 10.
     * @param sortBy  Campo por el cual se ordenará, por defecto "lastName".
     * @param sortDir Dirección de ordenación: "asc" o "desc", por defecto "asc".
     * @return Lista de DTOs con información resumida de pacientes.
     */
    @GetMapping("/patients/summary")
    public ResponseEntity<List<PatientSummaryResponse>> listPatientSummary(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "lastName") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Determinar dirección de ordenación
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Crear pageable de Spring Data
        Pageable pageable = PageRequest.of(page, size, sort);

        // Obtener página de pacientes
        Page<Patient> patientPage = patientRepository.findAllByActiveTrue(pageable);

        // Mapear cada Patient a DTO de resumen
        List<PatientSummaryResponse> summaryList = patientPage.getContent().stream()
                .map(PatientSummaryResponse::from)
                .toList();

        return ResponseEntity.ok(summaryList);
    }
    @PutMapping("/patients")
    @Transactional
    public ResponseEntity<PatientResponseData> updatePatient(@RequestBody @Valid PatientUpdateData data) {

        // Buscar paciente por ID
        Patient patient = patientRepository.findById(data.id())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + data.id()));

        // Validar que el email no exista en otro paciente
        if (patientRepository.existsByEmailAndIdNot(data.email(), data.id())) {
            throw new IllegalArgumentException("El correo ya está registrado por otro paciente");
        }

        // Actualizar datos mediante método encapsulado
        patient.updatePatient(data);

        // Guardar cambios
        patientRepository.save(patient);

        // Preparar DTO de respuesta
        PatientResponseData response = new PatientResponseData(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument(),
                patient.getBirthDate(),
                new AddressResponseData(patient.getAddress())
        );

        return ResponseEntity.ok(response);
    }
    @Transactional
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente no encontrado con id: " + id)
                );

        patient.delete();

        return ResponseEntity.noContent().build();
    }
}