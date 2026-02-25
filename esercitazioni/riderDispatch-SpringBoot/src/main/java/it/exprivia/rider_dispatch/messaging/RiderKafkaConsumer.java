package it.exprivia.rider_dispatch.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import it.exprivia.rider_dispatch.service.impl.DeliveryAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class RiderKafkaConsumer {

    private final DeliveryAssignmentService service;

   @KafkaListener(topics = "rider-updates", groupId = "rider-group")
    public void listenRiderUpdates(RiderDTO riderDTO) {
        log.info("=== LISTENER ATTIVO === {}", riderDTO);
        service.processRiderAvailability(riderDTO);
    }
}
