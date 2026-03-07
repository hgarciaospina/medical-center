package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.dto.AddressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;

    public Address(AddressData addressData) {
        this.state = addressData.state();
        this.city = addressData.city();
        this.street = addressData.street();
        this.number =  addressData.number();
        this.complement =  addressData.complement();
    }
}