package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;

/**
 * JPA entity representing a Doctor.
 * Lombok used only for getters.
 */
@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of =  "id")
public class Doctor {

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
    @Column(nullable = false)
    private String phone;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    @Column(nullable = false, unique = true)
    private String document;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;

    @NotNull
    @Valid
    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData data) {
        this.id = null;
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.phone = data.phone();
        this.document = data.document();
        this.specialty = data.specialty();
        this.address = new Address(data.addressData());
    }
}