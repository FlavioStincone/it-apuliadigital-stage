package com.example.userManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.exprovia.userManager.mapper.ClientMapper;
import it.exprovia.userManager.model.dto.ClientDTO;
import it.exprovia.userManager.model.entity.Client;
import it.exprovia.userManager.repository.ClientRepository;
import it.exprovia.userManager.service.impl.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest { 
    
    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Mock
    private ClientRepository mockRepository;

    @Mock
    private ClientMapper mockMapper;

	// @BeforeEach //il setup() può essere sostituito da @ExtendWith(MockitoExtension.class) e @Mock
    // void setup(){
    //     this.mockRepository = mock(ClientRepository.class);
    //     this.mockMapper = mock(ClientMapper.class);
    //     this.clientServiceImpl = new ClientServiceImpl(mockRepository, mockMapper); 
    // }

    @Nested
    class getClientsMethod{

        @Test
        void should_ReturCorrectListClient(){
        //given
        List<Client> clientsEntity = Arrays.asList(new Client("Lista1", "Lista1@gmail.com", "1234"), new Client("Lista2", "Lista2@gmail.com", "1234") );
        List<ClientDTO> expected = Arrays.asList(new ClientDTO("Lista1", "Lista1@gmail.com", "1234", null), new ClientDTO("Lista2", "Lista2@gmail.com", "1234", null) );

        when(mockRepository.findAll()).thenReturn(clientsEntity);
        when(mockMapper.toListDTO(clientsEntity)).thenReturn(expected);

        //When
        List<ClientDTO> clients = clientServiceImpl.getClients();

        //then
        assertEquals(expected, clients);

        }
    }

    @Nested
    class getClientMethod{

        @Test
        void should_ReturnNull_WhenIdNotFound()
        {
            //given
            when(mockRepository.findById(1L)).thenReturn(Optional.empty());

            //when
            ClientDTO actual = clientServiceImpl.getClient(1L);

            //then
            assertNull(actual);
            
        }

        @Test
        void should_ReturnCorrectClient() {
            
            //given
            Client clientEntity = new Client("Test", "Test@gmail.com", "1234");
            ClientDTO expected = new ClientDTO("Test", "Test@gmail.com", "1234", null);

            //when(mockRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
            given(mockRepository.findById(1L)).willReturn(Optional.of(clientEntity));// fa la stessa cosa di when() ma si utilizza uan nomenclatura corretta 
            given(mockMapper.toDTO(clientEntity)).willReturn(expected);

            //when
            ClientDTO actual = clientServiceImpl.getClient(1L);

            //then
            assertEquals(expected, actual);
        }

        @Disabled 
        @Test
        void should_ReturnCorrectClient_when_CalledMultipleTimes(){

            //given
            Client clientEntity = new Client("Test", "Test@gmail.com", "1234");
            ClientDTO expectedSecond = new ClientDTO("Test", "Test@gmail.com", "1234", null);

            when(mockRepository.findById(1L))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(clientEntity));

            when(mockMapper.toDTO(clientEntity)).thenReturn(expectedSecond);

            //when
            ClientDTO actualFirst = clientServiceImpl.getClient(1L);
            ClientDTO actualSecond = clientServiceImpl.getClient(1L);

            //then
            assertAll(
                () -> assertNull(actualFirst),
                () -> assertEquals(expectedSecond, actualSecond)
            );

        }
    }

    @Nested
    class createClientMethod{
        //utilizzo del verify() per verificare che un metodo sia stato chiamato
        @Test
        void should_CallRepositorySaveMethod(){
        
            //given
            ClientDTO clientDTO = new ClientDTO("Test", "Test@gmail.com", "1234", null);
            Client client = new Client("Test", "Test@gmail.com", "1234");

            //when(mockMapper.toEntity(clientDTO)).thenReturn(client);
            given(mockMapper.toEntity(clientDTO)).willReturn(client);

            //when
            clientServiceImpl.createClient(clientDTO);

            //then
            // verify(mockRepository).save(client); 
            then(mockRepository).should().save(client); // fa la stessa cosa di verify() ma si utilizza uan nomenclatura corretta
        }
   
        //Utilizzo di any()
        @Test
        void should_SaveClient_UsingAny() {
            // given
            ClientDTO clientDTO = new ClientDTO("Test", "Test@gmail.com", "1234", null);

            given(mockMapper.toEntity(any(ClientDTO.class))).willReturn(new Client("Test", "Test@gmail.com", "1234"));

            // when
            clientServiceImpl.createClient(clientDTO);

            // then
            then(mockRepository).should().save(any(Client.class));
        }
    }
}
