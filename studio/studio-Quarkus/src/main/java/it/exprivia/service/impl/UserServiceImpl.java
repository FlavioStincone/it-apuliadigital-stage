package it.exprivia.service.impl;

import java.util.List;

import it.exprivia.mapper.UserMapper;
import it.exprivia.model.User;
import it.exprivia.model.dto.UserDTO;
import it.exprivia.repository.UserRepository;
import it.exprivia.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserServiceImpl implements IUserService{

    @Inject
    UserRepository repository;

    @Inject
    UserMapper mapper;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO user) {

        if (repository.find("email", user.email()).firstResult() != null) {

            throw new WebApplicationException("Email already exists", Response.Status.CONFLICT);
            
        }

        User userEntity = mapper.toEntity(user);
        repository.persist(userEntity);
        return mapper.toDTO(userEntity);
    }

    @Override
    public UserDTO findById(Long id) {

        User userEntity = repository.findById(id);
        return mapper.toDTO(userEntity);
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> users = repository.findAll().list();
        return mapper.toDTOs(users);
    }

    @Transactional
    @Override
    public Boolean removeById(Long id) {

       return repository.deleteById(id);

    }
    
}
