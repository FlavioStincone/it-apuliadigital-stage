package it.exprovia.userManager.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.exprovia.userManager.mapper.ClientMapper;
import it.exprovia.userManager.model.dto.ClientDTO;
import it.exprovia.userManager.model.entity.Client;
import it.exprovia.userManager.model.entity.RoleEnum;
import it.exprovia.userManager.repository.ClientRepository;
import it.exprovia.userManager.service.IClient;

@Service
public class  ClientServiceImpl implements IClient {
    
    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    //Uso Dei Logger
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public List<ClientDTO> getClients() {
        // Restituisce tutte gli utenti presenti
        List<ClientDTO> ClientsDTO = mapper.toListDTO(repository.findAll());
        
        return ClientsDTO;
        //return repository.findAll().stream().map(mapper::toDTO).toList(); //(senza utilizzare il metodo toListDTO nel mapper)
    }

    @Override
    public ClientDTO getClient(Long id) {
        // Restituisce un utente specifica in base all'id
        logger.info("Fetching user with id: {"+ id +"}");
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        // Crea l'utente
        logger.info("Creating new user: {"+ clientDTO.name() +"}");
        Client client = mapper.toEntity(clientDTO);
        client.setRole(RoleEnum.CLIENT.getValue());
        repository.save(client);

        return mapper.toDTO(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<Client> existingClient = repository.findById(id);

        if (existingClient.isPresent()) {
            Client clientToUpdate = existingClient.get();

            clientToUpdate.setName(clientDTO.name());
            clientToUpdate.setEmail(clientDTO.email());
            clientToUpdate.setPwd(clientDTO.pwd());

            Client updatedClient = repository.save(clientToUpdate);

            return mapper.toDTO(updatedClient);
        }

        // Meglio lanciare un'eccezione o restituire Optional
        return null;
    }

    @Override
    public boolean deleteClient(Long id) {
        // Elimina l'utente 
        if (repository.existsById(id)) {
            logger.info("Deleting user with id: {"+ id +"}");
            repository.deleteById(id);
            return true;
        }
        return false; 
    }

    @Override
    public boolean login(String name, String pwd){

        List<Client> clientsByName = repository.findByName(name);

        if (!clientsByName.isEmpty()) {

            Client client = clientsByName.get(0);

            if (client.getPwd().equals(pwd)){
                return true;
            }
        }

        return false;

    }

    // Usare il costruttore per l'iniezione delle dipendenze per migliorare la testabilità e la chiarezza del codice.
    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    };

}
