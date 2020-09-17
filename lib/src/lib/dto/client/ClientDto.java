package lib.dto.client;

public class ClientDto {

    private int id;

    private String name;

    private AdressDto adress;



    public ClientDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdressDto getAdress() {
        return adress;
    }

    public void setAdress(AdressDto adress) {
        this.adress = adress;
    }
}
