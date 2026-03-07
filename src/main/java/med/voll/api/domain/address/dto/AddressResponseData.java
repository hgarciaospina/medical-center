package med.voll.api.domain.address.dto;

import med.voll.api.domain.address.Address;

public record AddressResponseData(
        String state,
        String city,
        String street,
        String number,
        String complement
) {
    public AddressResponseData(Address address) {
        this(
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement()
        );
    }
}