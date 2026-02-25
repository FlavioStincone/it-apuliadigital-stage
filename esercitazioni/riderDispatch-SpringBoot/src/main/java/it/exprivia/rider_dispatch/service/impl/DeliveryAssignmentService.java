package it.exprivia.rider_dispatch.service.impl;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import it.exprivia.rider_dispatch.service.IDeliveryAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryAssignmentService implements IDeliveryAssignmentService {

    public void processRiderAvailability(RiderDTO rider) {
        log.info("SISTEMA LOGISTICA: Analisi posizione per il Rider {}", rider.fiscalCode());
        
        if ("AVAILABLE".equals(rider.riderStatus().toString())) {
            log.info("SISTEMA LOGISTICA: Il Rider è pronto. Cerco ordini vicini alle coordinate [{}, {}]...", rider.lastLatitude(), rider.lastLongitude());
        } else {
            log.info("SISTEMA LOGISTICA: Rider non disponibile per nuovi ordini.");
        }
    }
}