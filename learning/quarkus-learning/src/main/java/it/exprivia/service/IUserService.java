package it.exprivia.service;

import java.util.List;

import it.exprivia.model.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface IUserService {
    
    public UserDTO createUser(UserDTO user);

    public UserDTO findById(Long id);

    public List<UserDTO> findAll();

    public Boolean removeById(Long id);
}
