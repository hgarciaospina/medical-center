package med.voll.api.domain.patient;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import med.voll.api.domain.patient.dto.PatientUpdateData;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^(3\\d{9}|\\d{7})$")
    @Column(nullable = false)
    private String phone;

    @NotBlank
    @Pattern(regexp = "\\d{6,10}")
    @Column(nullable = false, unique = true)
    private String document;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Valid
    @Embedded
    private Address address;

    // Constructor desde DTO
    public Patient(PatientRegistrationData data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.phone = data.phone();
        this.document = data.document();
        this.birthDate = data.birthDate();
        this.address = new Address(data.addressData()); // ✅ Mapea DTO a embebido
    }
    /**
     * Método profesional para actualizar un paciente
     */
    public void updatePatient(PatientUpdateData data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.phone = data.phone();
        this.document = data.document();
        this.birthDate = data.birthDate();
        this.address.updateAddress(data.addressData());
    }
}