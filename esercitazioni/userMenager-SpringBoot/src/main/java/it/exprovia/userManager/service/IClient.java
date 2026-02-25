package it.exprovia.userManager.service;

import java.util.List;

import it.exprovia.userManager.model.dto.ClientDTO;

public interface IClient {

    List<ClientDTO> getClients();

    ClientDTO getClient(Long id);

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    boolean deleteClient(Long id);

    boolean login(String name, String pwd);
    
}
