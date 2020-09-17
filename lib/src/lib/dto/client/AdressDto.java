package lib.dto.client;

public class AdressDto {

    private String street;

    private String number;

    public AdressDto(String street, String number) {
        this.street = street;
        this.number = number;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
