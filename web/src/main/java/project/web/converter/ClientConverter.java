package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.Client;
import project.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .email(client.getEmail())
                .name(client.getName())
                .phoneNumber(client.getPhoneNumber())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
