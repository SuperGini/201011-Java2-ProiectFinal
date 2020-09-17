package lib.dto.client;

public class PersonDto extends ClientDto{

    private String cnp;

    public PersonDto(int id, String name, AdressDto adress, String cnp) {
        super(id, name, adress);
        this.cnp = cnp;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}
