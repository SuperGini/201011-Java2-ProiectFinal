package lib.dto.client;

public class CompanyDto extends ClientDto{

    private String cui;

    public static class Builder{
        private CompanyDto companyDto = new CompanyDto();

        public Builder setCuiDto(String cuiDto){
            companyDto.cui = cuiDto;
            return this;
        }

        public Builder setIdDto(int idDto){
            companyDto.setId(idDto);
            return this;
        }

        public Builder setNameDto(String nameDto){
            companyDto.setName(nameDto);
            return this;
        }

        public Builder setAdressDto(AdressDto adress){
          companyDto.setAdress(adress);
          return this;
        }

        public CompanyDto build(){
            return companyDto;
        }


    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

}
