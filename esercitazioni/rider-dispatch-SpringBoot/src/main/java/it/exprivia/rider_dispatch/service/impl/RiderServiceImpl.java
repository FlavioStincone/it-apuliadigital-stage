package it.exprivia.rider_dispatch.service.impl;

import it.exprivia.rider_dispatch.exception.RiderNotFoundException;
import it.exprivia.rider_dispatch.mapper.RiderMapper;
import it.exprivia.rider_dispatch.messaging.RiderKafkaProducer;
import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import it.exprivia.rider_dispatch.model.entity.Rider;
import it.exprivia.rider_dispatch.repository.RiderRepository;
import it.exprivia.rider_dispatch.service.IRiderService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements IRiderService {

    private final RiderRepository repository;
    private final RiderMapper mapper;
    private final RiderKafkaProducer producer;

    @Override
    @Transactional // serve per la consistenza DB + Kafka
    public RiderDTO updateRider(RiderDTO riderDTO) {

        Rider riderEntity = mapper.toEntity(riderDTO);

        Rider savedRider = repository.save(riderEntity);

        RiderDTO resultDTO = mapper.toDTO(savedRider); 

        producer.sendRiderUpdate(resultDTO); 
        log.info("Aggiornamento inviato a Kafka per: {}", resultDTO.fiscalCode());
        
        
        return resultDTO;
    }

    @Override
    @Transactional(readOnly = true) //qui non esegue il Dirty Checking (il controllo sull'oggetto attuale con l'originale)
    public List<RiderDTO> getAllRiders() {
        return mapper.toDTOs(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public RiderDTO getRiderByFiscalCode(String fiscalCode) {

        Rider rider = repository.findById(fiscalCode).orElseThrow( () ->new RiderNotFoundException( "Rider with Fiscal Code " + fiscalCode + " not found"));

        return mapper.toDTO(rider);
    }

    @Override
    @Transactional
    public Boolean removeRider(String fiscalCode){

        if (!repository.existsById(fiscalCode)) {
            throw new RiderNotFoundException("Rider with Fiscal Code " + fiscalCode + " not found");
        }

        repository.deleteById(fiscalCode);
        
        return true;
    }
}