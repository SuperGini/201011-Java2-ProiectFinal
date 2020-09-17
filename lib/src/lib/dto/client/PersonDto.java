package lib.dto.client;

public class PersonDto extends ClientDto{

    private String cnp;

    public PersonDto() {
    }

    public static class Builder{
        private PersonDto instance = new PersonDto();


        public Builder setAdresaDto(AdressDto adresaDto){
            instance.setAdress(adresaDto);
            return this;
        }

        public Builder setNameDto(String name){
            instance.setName(name);
            return this;
        }

        public Builder setCnpDto(String cnp){
            instance.cnp = cnp;
            return this;
        }

        public Builder setIdDto(int id){
            instance.setId(id);
            return this;
        }

        public PersonDto build(){
            return instance;
        }
    }


    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}
