package it.exprivia.rider_dispatch.messaging;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RiderKafkaProducer {

    // spring inietta automaticamente questo template usando le config dello YAML
    private final KafkaTemplate<String, RiderDTO> kafkaTemplate;

    private static final String TOPIC = "rider-updates";

    public void sendRiderUpdate(RiderDTO riderDTO) {

        log.info("INVIO MESSAGGIO A KAFKA Topic: {}, Rider: {}", TOPIC, riderDTO.fiscalCode());

        // cod fiscale lo usiamo effettivamente come key 
        kafkaTemplate.send(TOPIC, riderDTO.fiscalCode(), riderDTO);
    }
}