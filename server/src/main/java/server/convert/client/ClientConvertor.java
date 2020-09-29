package server.convert.client;

import lib.dto.client.ClientDto;
import server.model.client.Client;

public class ClientConvertor {

    private ClientConvertor() {
    }


    public static ClientDto convert(Client client){
        ClientDto clientDto = new ClientDto();
                    clientDto.setId(client.getId());
                    clientDto.setName(client.getName());

        return clientDto;

    }

    public static Client convert(ClientDto clientDto){
           Client client = new Client();
                    client.setId(clientDto.getId());
                    client.setName(clientDto.getName());

           return client;

    }

}
