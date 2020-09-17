package lib.dto.client;

public class CompanyDto extends ClientDto{

    private String cui;

    public CompanyDto(int id, String name, AdressDto adress, String cui) {
        super(id, name, adress);
        this.cui = cui;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }
}
