**STUDIO DELLE NUOVE DIPENDENZE**

- **Spring for Apache Kafka**: Fornisce il KafkaTemplate e tutta l'infrastruttura di messaging
- **Spring Boot Actuator**: serve per monitorare lo stato di salute dell'app (health check)

 **KAFKA**
   è meglio usare .yml e non .proprierties per Kafka perchè 
   kafka possiede tante proprietà e con il .yml essendo gerarchico, 
   permette di raggruppare le proprietà sotto un'unica radice, 
   evitando la ripetizione inutile dei prefissi


**?UPDATE?**

- Classe ORdine composta da Corriere e Pacco 1 a Molti
- E se il numero ordine per l'utente fosse una Stringa ("OD-1"...) creato con il concat ("OD-"+ id)?
  per il database è id (1,2,3) invece l'utente vede ("OD-001"...), e per cercarlo si toglie il concat


  zookeeper:
    image: bitnami/zookeeper:3.9
    container_name: zookeeper
    ports:
      - "2181:2181"
    environment:
      ALLOW_ANONYMOUS_LOGIN: yes

  kafka:
    image: bitnami/kafka:3.5
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_CFG_LISTENERS: PLAINTEXT_INTERNAL://:29092,PLAINTEXT_EXTERNAL://:9092
      KAFKA_CFG_ADVERTISED_LISTENERS: PLAINTEXT_INTERNAL://kafka:29092,PLAINTEXT_EXTERNAL://localhost:9092
      KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE: "true"
      KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT_INTERNAL:PLAINTEXT,PLAINTEXT_EXTERNAL:PLAINTEXT
      ALLOW_PLAINTEXT_LISTENER: "yes"
      KAFKA_CFG_INTER_BROKER_LISTENER_NAME: PLAINTEXT_INTERNAL
    depends_on:
      - zookeeper

  kafka-ui:
    image: provectuslabs/kafka-ui
    container_name: kafka-ui
    depends_on:
      - kafka
    ports:
      - "18080:8080"
    environment:
      DYNAMIC_CONFIG_ENABLED: true
      KAFKA_CLUSTERS_0_NAME: local
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:29092

  producer:
    build: producer-app/.
    depends_on:
      - kafka
    volumes:
      - ./producer-app/src:/src/
      - ./producer-app/events:/app/
 