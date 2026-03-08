package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.dto.AddressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    // Constructor para mapear desde DTO
    public Address(AddressData dto) {
        this.state = dto.state();
        this.city = dto.city();
        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
    }

    /**
     * Actualiza los campos de la dirección usando AddressData
     */
    public void updateAddress(AddressData data) {
        this.state = data.state();
        this.city = data.city();
        this.street = data.street();
        this.number = data.number();
        this.complement = data.complement();
    }
}