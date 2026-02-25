package it.exprovia.userManager.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.exprovia.userManager.model.dto.ClientDTO;
import it.exprovia.userManager.model.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    
    Client toEntity(ClientDTO clientDTO);
    
    List<ClientDTO> toListDTO(List<Client> clients);
    
    List<Client> toListEntity(List<ClientDTO> clientsDTO);
}